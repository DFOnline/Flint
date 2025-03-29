package dev.dfonline.flint.template.value;

public enum ParticleSetting {

    RGB("rgb"),
    COLOR_VARIATION("colorVariation"),
    SIZE("size"),
    SIZE_VARIATION("sizeVariation");

    private final String key;

    ParticleSetting(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
