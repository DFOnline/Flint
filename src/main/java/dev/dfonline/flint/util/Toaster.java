package dev.dfonline.flint.util;

import dev.dfonline.flint.Flint;
import net.kyori.adventure.text.Component;
import net.minecraft.client.gui.components.toasts.SystemToast;

public final class Toaster {

    private Toaster() {
    }

    public static void toast(Component title, Component description) {
        SystemToast.add(Flint.getClient().gui.toastManager(), SystemToast.SystemToastId.UNSECURE_SERVER_WARNING, Flint.AUDIENCE.asNative(title), Flint.AUDIENCE.asNative(description));
    }

}
