package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.ChatListeningFeature;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.hypercube.Node;
import dev.dfonline.flint.util.ComponentUtil;
import dev.dfonline.flint.util.message.impl.prefix.ErrorMessage;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class ActionDumpGetterFeature implements ChatListeningFeature, PacketListeningFeature {

    public static StringBuilder capturedData = null;
    private static boolean isGettingActionDump = false;
    private static ComponentUtil.ColorMode colorMode = ComponentUtil.ColorMode.SECTION;
    private static int lines;
    private static int length;
    private static long startTime;

    public static void getActionDump(ComponentUtil.ColorMode colorMode, boolean allowNonObtainableActionDumpNodes) {
        if (isGettingActionDump) {
            return;
        }

        Node node = Flint.getUser().getNode();

        if (!allowNonObtainableActionDumpNodes && (node == null || node.isActionDumpObtainable())) {
            Flint.getUser().sendMessage(new ErrorMessage("flint.command.flint.action_dump.fail.node"));
            return;
        }

        isGettingActionDump = true;
        ClientPlayNetworkHandler networkHandler = Flint.getClient().getNetworkHandler();

        if (networkHandler == null) {
            isGettingActionDump = false;
            return;
        }

        networkHandler.sendCommand("dumpactioninfo");
        capturedData = new StringBuilder();
        startTime = System.currentTimeMillis();
    }

    @Override
    public EventResult onChatMessage(Text text, boolean actionbar) {
        if (capturedData == null || !isGettingActionDump) {
            return EventResult.PASS;
        }

        if (text.getString().startsWith("Error: ")) {
            isGettingActionDump = false;
            Flint.getUser().sendMessage(new ErrorMessage("flint.command.flint.action_dump.fail.start"));
            return EventResult.CANCEL;
        }

        ComponentUtil.textToString(text, capturedData, colorMode);
        String content = text.getString();
        capturedData.append("\n");
        lines += 1;
        length += content.length();

        if (text.getString().equals("}")) {
            isGettingActionDump = false;
            try {
                // TODO: Save capturedData.toString() to .mods/Flint/actiondump.json
            } catch (IOException e) {
                // TODO: Send to player
            }
        }

        return EventResult.CANCEL;
    }
}
