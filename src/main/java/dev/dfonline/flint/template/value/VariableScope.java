package dev.dfonline.flint.template.value;

import java.util.HashMap;
import java.util.Map;

public enum VariableScope {

    LINE("line"),
    LOCAL("local"),
    GAME("unsaved"),
    SAVE("saved");

    private static final Map<String, VariableScope> SCOPE_MAP = new HashMap<>();

    static {
        for (VariableScope scope : values()) {
            SCOPE_MAP.put(scope.id, scope);
        }
    }

    private final String id;

    VariableScope(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static VariableScope fromId(String id) {
        return SCOPE_MAP.get(id);
    }

}
