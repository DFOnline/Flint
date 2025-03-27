package dev.dfonline.flint.templates;

public enum VariableScope {
    LINE("line"),
    LOCAL("local"),
    GAME("unsaved"),
    SAVE("saved");

    public final String internalName;
    VariableScope(String internalName) {
        this.internalName = internalName;
    }

    public static VariableScope fromInternalName(String internalName) {
        for (VariableScope variableScope : values()) {
            if (variableScope.internalName.equals(internalName)) {
                return variableScope;
            }
        }
        return null;
    }
}
