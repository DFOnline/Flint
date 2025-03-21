package dev.dfonline.flint;

import dev.dfonline.flint.event.ModeSwitchCallback;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.message.Message;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.ApiStatus;

/**
 * Stores additional information about the client player.
 */
public final class User {

    private Mode mode;
    private Plot plot;

    public ClientPlayerEntity getPlayer() {
        ClientPlayerEntity player = Flint.getClient().player;

        assert player != null : "Player is null";
        return player;
    }

    public Mode getMode() {
        return this.mode;
    }

    @ApiStatus.Internal
    public void setMode(Mode mode) {
        Mode oldMode = this.mode;
        this.mode = mode;
        ModeSwitchCallback.EVENT.invoker().switchMode(mode, oldMode);
    }

    public Plot getPlot() {
        return this.plot;
    }

    @ApiStatus.Internal
    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public void sendMessage(Message message) {
        message.send();
    }

}
