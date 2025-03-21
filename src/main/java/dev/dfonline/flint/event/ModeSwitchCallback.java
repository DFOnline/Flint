package dev.dfonline.flint.event;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Plot;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ModeSwitchCallback {

    Event<ModeSwitchCallback> EVENT = EventFactory.createArrayBacked(ModeSwitchCallback.class,
            (listeners) -> (plot, newMode, oldMode) -> {
                Flint.getUser().setPlot(plot);
                Flint.getUser().setMode(newMode);

                for (ModeSwitchCallback listener : listeners) {
                    listener.switchMode(plot, newMode, oldMode);
                }
            });

    void switchMode(Plot plot, Mode newMode, Mode oldMode);

}
