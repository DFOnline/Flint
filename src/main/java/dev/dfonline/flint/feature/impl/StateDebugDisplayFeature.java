package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.User;
import dev.dfonline.flint.feature.trait.ModeSwitchListeningFeature;
import dev.dfonline.flint.feature.trait.RenderedFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.util.PaletteColor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

import java.util.ArrayList;

import static net.minecraft.text.Text.literal;

public class StateDebugDisplayFeature implements RenderedFeature, ModeSwitchListeningFeature {
    private int y;

    @Override
    public void render(DrawContext draw, RenderTickCounter renderTickCounter) {
        User user = Flint.getUser();
        ArrayList<Text> texts = new ArrayList<>();
        texts.add(literal("General State:").withColor(PaletteColor.PURPLE.value()));
        texts.add(fancy("Node = ", (user.getNode() == null) ? "null" : user.getNode().getName()));
        texts.add(fancy("Plot = ", (user.getPlot() == null) ? "null" : (user.getPlot().getId() + "")));
        texts.add(fancy("Mode = ", (user.getMode() == null) ? "null" : (user.getMode().getName())));
        texts.add(fancy("Dev Origin Location = ", ((user.getPlot() == null || user.getPlot().getOrigin() == null) ? "null" : user.getPlot().getOrigin().toString())));

        render(texts, draw);
    }

    private static Text fancy(String first, String second) {
        return literal(first).withColor(PaletteColor.PURPLE.value())
                .append(literal(second).withColor(PaletteColor.PURPLE_LIGHT.value()));
    }

    private void render(ArrayList<Text> texts, DrawContext draw) {
        y = 5;
        for (int i = 0; i < texts.size(); i++) {
            Text text = texts.get(i);
            if (i == 0) {
                draw.drawTextWithShadow(Flint.getClient().textRenderer, text, 5, y, 0xFF_FFFFFF);
                y += 2;
            } else {
                draw.drawTextWithShadow(Flint.getClient().textRenderer, text, 5 + 5, y, 0xFF_FFFFFF);
            }
            y += Flint.getClient().textRenderer.fontHeight + 2;
        }
    }

    @Override
    public boolean isEnabled() {
        return FlintAPI.isDebugging();
    }

    @Override
    public void onSwitchMode(Mode oldMode, Mode newMode) {
        if (Flint.getClient().player == null) {
            System.out.println("NULL PLAYER WHAHAHAA");
            return;
        }

        Flint.getUser().getPlayer().sendMessage(literal("Old: " + oldMode.getName() + " -> New: " + newMode.getName()), false);
        Flint.getUser().getPlayer().sendMessage(literal((Flint.getUser().getPlot() == null) ? "No Plot" : Flint.getUser().getPlot().toReadableString()), false);

    }
}

