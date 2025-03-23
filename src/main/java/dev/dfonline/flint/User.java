package dev.dfonline.flint;

import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.SwitchModeListeningFeature;
import dev.dfonline.flint.feature.trait.SwitchPlotListeningFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Node;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.message.Message;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Stores additional information about the client player.
 */
public final class User {

    private Mode mode;
    private @Nullable Plot plot;
    private @Nullable Node node;

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
        if (this.mode != mode) {
            Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.SWITCH_MODE_LISTENING).forEach(feature -> {
                ((SwitchModeListeningFeature) feature).onSwitchMode(this.mode, mode);
            });
        }
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
            Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.SWITCH_PLOT_LISTENING).forEach(feature -> {
                ((SwitchPlotListeningFeature) feature).onSwitchPlot(this.plot, plot);
            });
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
