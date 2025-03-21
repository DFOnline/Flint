package dev.dfonline.flint.event;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.hypercube.Mode;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ModeSwitchCallback {

    Event<ModeSwitchCallback> EVENT = EventFactory.createArrayBacked(ModeSwitchCallback.class,
            (listeners) -> (newMode, oldMode) -> {
                Flint.getUser().setMode(newMode);

                for (ModeSwitchCallback listener : listeners) {
                    listener.switchMode(newMode, oldMode);
                }
            });

    void switchMode(Mode newMode, Mode oldMode);

}
