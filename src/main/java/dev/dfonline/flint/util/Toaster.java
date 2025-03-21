package dev.dfonline.flint.util;

import dev.dfonline.flint.Flint;
import net.kyori.adventure.text.Component;
import net.minecraft.client.toast.SystemToast;

public final class Toaster {

    private Toaster() {
    }

    public static void toast(Component title, Component description) {
        Flint.getClient().getToastManager().add(SystemToast.create(Flint.getClient(), SystemToast.Type.UNSECURE_SERVER_WARNING, Flint.AUDIENCE.asNative(title), Flint.AUDIENCE.asNative(description)));
    }

}
