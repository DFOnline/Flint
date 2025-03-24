package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.hypercube.Plot;

/**
 * A feature that listens for plot switches in DiamondFire.
 */
public interface PlotSwitchListeningFeature extends FeatureTrait {

    /**
     * Called when the user switches between plots.
     *
     * @param oldPlot The plot the user was in, it may be null if not in a plot before
     * @param newPlot The plot the user switched to, it may be null if leaving a plot
     */
    void onSwitchPlot(Plot oldPlot, Plot newPlot);

}
