package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.ChatListeningFeature;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.hypercube.Node;
import dev.dfonline.flint.util.ComponentUtil;
import dev.dfonline.flint.util.file.ExternalFile;
import dev.dfonline.flint.util.file.FileUtil;
import dev.dfonline.flint.util.message.impl.prefix.ErrorMessage;
import dev.dfonline.flint.util.message.impl.prefix.SuccessMessage;
import dev.dfonline.flint.util.result.EventResult;
import net.kyori.adventure.text.Component;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;

import java.io.IOException;

public class ActionDumpGetterFeature implements ChatListeningFeature, PacketListeningFeature {

    private static final int MS_IN_SEC = 1000;
    private static StringBuilder capturedData = null;
    private static boolean isGettingActionDump = false;
    private static ComponentUtil.ColorMode colorMode;
    private static int lines;
    private static int length;
    private static long startTime;

    public static void getActionDump(ComponentUtil.ColorMode color, boolean allowNonObtainableActionDumpNodes) {
        if (isGettingActionDump) {
            return;
        }

        Node node = Flint.getUser().getNode();

        if (!allowNonObtainableActionDumpNodes && (node == null || !node.isActionDumpObtainable())) {
            Flint.getUser().sendMessage(new ErrorMessage("flint.command.flint.action_dump.fail.node"));
            return;
        }

        isGettingActionDump = true;
        ClientPlayNetworkHandler networkHandler = Flint.getClient().getNetworkHandler();

        if (networkHandler == null) {
            isGettingActionDump = false;
            return;
        }

        capturedData = new StringBuilder();
        colorMode = color;
        lines = 0;
        length = 0;
        startTime = System.currentTimeMillis();
        networkHandler.sendCommand("dumpactioninfo");
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
        Flint.getUser().sendMessage(new SuccessMessage("flint.command.flint.action_dump.progress", true, Component.text((float) (System.currentTimeMillis() - startTime) / MS_IN_SEC), Component.text(lines), Component.text(length)));

        if (text.getString().equals("}")) {
            isGettingActionDump = false;
            try {
                FileUtil.writeFile(ExternalFile.ACTION_DUMP.getPath(), capturedData.toString());
                Flint.getUser().sendMessage(new SuccessMessage("flint.command.flint.action_dump.success", Component.text((float) (System.currentTimeMillis() - startTime) / MS_IN_SEC), Component.text(lines), Component.text(length)));
            } catch (IOException e) {
                Flint.getUser().sendMessage(new ErrorMessage("flint.command.flint.action_dump.fail.write"));
                return EventResult.CANCEL;
            } finally {
                // Let garbage collector do its job.
                capturedData = null;
            }
        }

        return EventResult.CANCEL;
    }

}
