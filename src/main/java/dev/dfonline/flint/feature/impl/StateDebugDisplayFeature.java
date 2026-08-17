package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.User;
import dev.dfonline.flint.feature.trait.ModeSwitchListeningFeature;
import dev.dfonline.flint.feature.trait.RenderedFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Node;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.Logger;
import dev.dfonline.flint.util.ObjectUtil;
import dev.dfonline.flint.util.PaletteColor;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;

import static net.minecraft.network.chat.Component.literal;

public class StateDebugDisplayFeature implements RenderedFeature {

    private static final Logger LOGGER = Logger.of(StateDebugDisplayFeature.class);
    private static final int STARTING_Y = 5;
    private static final int STARTING_X = 5;

    @Override
    public void render(GuiGraphicsExtractor draw, DeltaTracker renderTickCounter) {
        User user = Flint.getUser();

        ArrayList<Component> texts = new ArrayList<>();
        texts.add(literal("General State:").withColor(PaletteColor.PURPLE.value()));
        texts.add(formatValue("Node", ObjectUtil.toString(user.getNode(), Node::getName)));
        texts.add(formatValue("Node Id", user.getNodeId() + ""));
        texts.add(formatValue("Plot", ObjectUtil.toString(user.getPlot(), plot -> plot.getId() + "")));
        texts.add(formatValue("Plot Size", ObjectUtil.toString(user.getPlot(), plot -> plot.getSize().name())));
        texts.add(formatValue("Plot Size Is Known", ObjectUtil.toString(user.getPlot(), plot -> plot.isSizeKnown() + "")));
        texts.add(formatValue("Plot Has Underground", ObjectUtil.toString(user.getPlot(), plot -> plot.hasUnderground() + "")));
        texts.add(formatValue("Mode", ObjectUtil.toString(user.getMode(), Mode::getName)));
        texts.add(formatValue("Dev Origin Location",
                ObjectUtil.toString(user.getPlot(), plot -> ObjectUtil.toString(plot.getDevOrigin(), Object::toString))
        ));
        texts.add(formatValue("Owner", ObjectUtil.toString(user.getPlot(), Plot::getOwner)));

        this.renderTexts(texts, draw);
    }

    private void renderTexts(ArrayList<Component> texts, GuiGraphicsExtractor draw) {
        int y = STARTING_Y;
        for (int i = 0; i < texts.size(); i++) {
            Component text = texts.get(i);
            if (i == 0) {
                draw.text(Flint.getClient().font, text, STARTING_X, y, 0xFF_FFFFFF);
                y += 2;
            } else {
                draw.text(Flint.getClient().font, text, STARTING_X + STARTING_Y, y, 0xFF_FFFFFF);
            }
            y += Flint.getClient().font.lineHeight + 2;
        }
    }

    private static Component formatValue(String key, String value) {
        return literal(key).withColor(PaletteColor.PURPLE.value())
                .append(literal(" = ").withColor(PaletteColor.GRAY_DARK.value()))
                .append(literal(value).withColor(PaletteColor.PURPLE_LIGHT.value()));
    }

    @Override
    public boolean isEnabled() {
        return FlintAPI.isDebugging();
    }

}

