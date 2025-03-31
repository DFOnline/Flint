package dev.dfonline.flint.template;

import dev.dfonline.flint.template.value.Value;

public class ArgumentBuilder {
    public static ArgumentBuilder create() {
        return new ArgumentBuilder();
    }

    private final ArgumentContainer container = new ArgumentContainer();

    public ArgumentBuilder set(int slot, Value value) {
        container.set(slot, value);
        return this;
    }

    public ArgumentContainer build() {
        return container;
    }
}
