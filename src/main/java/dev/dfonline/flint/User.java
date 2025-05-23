package dev.dfonline.flint;

import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.ModeSwitchListeningFeature;
import dev.dfonline.flint.feature.trait.PlotSwitchListeningFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Node;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.message.Message;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Stores additional information about the client player.
 */
public final class User {

    private @NotNull Mode mode = Mode.NONE;
    private @Nullable Plot plot;
    private @Nullable Node node;

    public ClientPlayerEntity getPlayer() {
        ClientPlayerEntity player = Flint.getClient().player;

        if (player == null) {
            throw new NullPointerException("Player is null, User#getPlayer should only be used as a shorthand when it is known that the player is not null.");
        }
        return player;
    }

    public @NotNull Mode getMode() {
        return this.mode;
    }

    @ApiStatus.Internal
    public void setMode(@NotNull Mode mode) {
        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.MODE_SWITCH_LISTENING).forEach(feature ->
                ((ModeSwitchListeningFeature) feature).onSwitchMode(this.mode, mode)
        );
        this.mode = mode;
    }

    public @Nullable Plot getPlot() {
        return this.plot;
    }

    @ApiStatus.Internal
    public void setPlot(@Nullable Plot plot) {
        boolean shouldTriggerEvent = false;
        if (this.plot == null || plot == null) {
            if (this.plot != plot) {
                shouldTriggerEvent = true;
            }
        } else {
            if (!this.plot.equals(plot)) {
                shouldTriggerEvent = true;
            }
        }
        if (shouldTriggerEvent) {
            Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.PLOT_SWITCH_LISTENING).forEach(feature ->
                    ((PlotSwitchListeningFeature) feature).onSwitchPlot(this.plot, plot)
            );
        }
        this.plot = plot;
    }

    public @Nullable Node getNode() {
        return this.node;
    }

    @ApiStatus.Internal
    public void setNode(@Nullable Node node) {
        this.node = node;
    }

    public void sendMessage(Message message) {
        message.send();
    }

}
