package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.EventBlock;

/**
 * {
 * id: "block",
 * block: "entity_event",
 * args: { // always empty, and always exists
 * items: []
 * },
 * action: "<action name or empty string>",
 * attribute: "<either LS-CANCEL or field is not set>"
 * }
 */
public class EntityEvent extends EventBlock {

    public EntityEvent(String action) {
        super("entity_event", action);
    }

    public EntityEvent(String action, boolean lsCancel) {
        super("entity_event", action, lsCancel);
    }

    public EntityEvent(String action, ArgumentContainer args) {
        super("entity_event", action, args);
    }

    public EntityEvent(String action, boolean lsCancel, ArgumentContainer args) {
        super("entity_event", action, lsCancel, args);
    }

}
