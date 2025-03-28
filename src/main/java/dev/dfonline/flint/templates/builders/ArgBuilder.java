package dev.dfonline.flint.templates.builders;

import dev.dfonline.flint.templates.Arguments;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class ArgBuilder {
    private Arguments arguments = new Arguments();
    public static ArgBuilder create() {
        return new ArgBuilder();
    }
    public ArgBuilder add(Argument argument) {
        arguments.add(argument);
        return this;
    }
    public Arguments build() {
        return arguments;
    }
}
