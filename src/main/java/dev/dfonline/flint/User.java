package dev.dfonline.flint;

import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.SwitchModeListeningFeature;
import dev.dfonline.flint.feature.trait.SwitchPlotListeningFeature;
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
        if (this.mode != mode) {
            Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.SWITCH_MODE_LISTENING).forEach(feature -> {
                ((SwitchModeListeningFeature) feature).onSwitchMode(this.mode, mode);
            });
        }
        this.mode = mode;
    }

    public Plot getPlot() {
        return this.plot;
    }

    @ApiStatus.Internal
    public void setPlot(Plot plot) {
        boolean shouldTriggerEvent = false;
        if (!(this.plot == null == plot == null)) {
            // Whether the current plot was null and now isn't or it wasn't null but now is
            shouldTriggerEvent = true;
        } else {
            // Both plots are not null, check whether they are equal
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

    public void sendMessage(Message message) {
        message.send();
    }

}
