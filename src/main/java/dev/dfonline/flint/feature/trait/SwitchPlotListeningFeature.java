package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.hypercube.Plot;

public interface SwitchPlotListeningFeature extends FeatureTrait {
    void onSwitchPlot(Plot oldPlot, Plot newPlot);
}
