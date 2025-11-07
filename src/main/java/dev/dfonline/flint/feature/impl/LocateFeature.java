package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Node;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.Toaster;
import dev.dfonline.flint.util.result.EventResult;
import it.unimi.dsi.fastutil.Pair;
import net.kyori.adventure.text.Component;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles requesting locate commands and serving their responses in a structured manner.
 */
public class LocateFeature implements PacketListeningFeature {

    private static final Pattern LOCATE_PATTERN = Pattern.compile("\\s{39}\\n(?:You are|(?<username>[A-Za-z0-9_]+) is) currently (?<mode>playing|coding|building|at spawn|existing)(?:(?: on:\\n)?\\n)?(?:→ (?<plotName>.+) \\[(?<plotID>\\d+)](?: \\[)?(?<plotHandle>[a-z0-9_-]+)?]? ?(?:\\n→ (?<status>.++))?\\n→ Owner: (?<owner>[A-Za-z0-9_]+)(?<whitelisted> \\[Whitelisted])?)? ?\\n?→ Server: (?<node>[\\w ?]+)\\n\\s{39}", Pattern.MULTILINE);
    private static final Queue<Pair<String, CompletableFuture<LocateResult>>> locateRequests = new LinkedList<>();
    private static boolean awaitingResponse = false;
    private static final int LOCATE_TIMEOUT_SECONDS = 3;

    @Override
    public boolean alwaysOn() {
        return true;
    }

    public static CompletableFuture<LocateResult> requestLocate(String playerName) {
        CompletableFuture<LocateResult> locateResult = new CompletableFuture<>();
        Pair<String, CompletableFuture<LocateResult>> requestPair = Pair.of(playerName, locateResult);

        locateRequests.add(requestPair);

        CompletableFuture.delayedExecutor(LOCATE_TIMEOUT_SECONDS, TimeUnit.SECONDS).execute(() -> {
            if (!locateResult.isDone()) {
                locateResult.completeExceptionally(new TimeoutException("Locate request timed out after " + LOCATE_TIMEOUT_SECONDS + " second(s)"));
                Toaster.toast(Component.translatable("flint.locate.timeout.title"), Component.translatable("flint.locate.timeout.description", Component.text(LOCATE_TIMEOUT_SECONDS)));

                if (awaitingResponse && !locateRequests.isEmpty() && locateRequests.peek().second() == locateResult) {
                    locateRequests.poll();
                    awaitingResponse = false;
                    processNextRequestIfReady();
                } else {
                    locateRequests.removeIf(pair -> pair.second() == locateResult);
                }
            }
        });

        processNextRequestIfReady();

        return locateResult;
    }

    private static void processNextRequestIfReady() {
        if (!awaitingResponse && !locateRequests.isEmpty()) {
            awaitingResponse = true;
            Pair<String, CompletableFuture<LocateResult>> currentRequest = locateRequests.peek();

            CommandSenderFeature.queue("locate " + currentRequest.first());
        }
    }

    @Override
    public EventResult onReceivePacket(Packet<?> packet) {
        if (!(packet instanceof GameMessageS2CPacket message)) {
            return EventResult.PASS;
        }

        if (locateRequests.isEmpty() || !awaitingResponse) {
            return EventResult.PASS;
        }

        Pair<String, CompletableFuture<LocateResult>> currentRequest = locateRequests.peek();
        String playerName = currentRequest.first();

        String text = message.content().getString();

        Matcher matcher = LOCATE_PATTERN.matcher(Formatting.strip(text));

        if (!matcher.find()) {
            return EventResult.PASS;
        }

        LocateResult result = this.parseLocateResponse(matcher, playerName);

        if (result == null) {
            return EventResult.PASS;
        }

        locateRequests.poll();
        currentRequest.second().complete(result);
        awaitingResponse = false;

        processNextRequestIfReady();

        return EventResult.CANCEL;
    }

    private LocateResult parseLocateResponse(Matcher matcher, String playerName) {
        if (matcher.group("mode") == null || matcher.group("node") == null) {
            return null;
        }

        String username = playerName;
        if (matcher.group("username") != null) {
            username = matcher.group("username");
        }

        Mode mode;
        switch (matcher.group("mode")) {
            case "at spawn" -> mode = Mode.SPAWN;
            case "playing" -> mode = Mode.PLAY;
            case "coding" -> mode = Mode.DEV;
            case "building" -> mode = Mode.BUILD;
            // we assume 'existing' can only be achieved with code spectating.
            case "existing" -> mode = Mode.CODE_SPECTATE;
            // this is an impossible case.
            default -> mode = Mode.NONE;
        }

        Plot plot = parsePlot(matcher);

        Node node = Node.fromName(matcher.group("node"));

        return new LocateResult(username, mode, plot, node);
    }

    private static @Nullable Plot parsePlot(Matcher matcher) {
        if (matcher.group("plotName") == null) {
            return null;
        }

        String plotName = matcher.group("plotName");
        int plotID = Integer.parseInt(matcher.group("plotID"));
        String plotHandle = null;
        if (matcher.group("plotHandle") != null) {
            plotHandle = matcher.group("plotHandle");
        }
        boolean whitelisted = matcher.group("whitelisted") != null;

        String owner = matcher.group("owner");

        return new Plot(plotID, Text.literal(plotName), plotHandle, whitelisted, owner);
    }

    public record LocateResult(String player, Mode mode, @Nullable Plot plot, Node node) {
    }

}
