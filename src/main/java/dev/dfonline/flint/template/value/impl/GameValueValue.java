package dev.dfonline.flint.template.value.impl;

import dev.dfonline.flint.template.value.Value;

public class GameValueValue extends Value {

    private GameValueData data;

    public GameValueValue() {
        this.setId("g_val");
    }

    public GameValueValue(String type, String target) {
        this();
        this.data = new GameValueData(type, target);
    }

    public GameValueData getData() {
        return this.data;
    }

    public void setData(GameValueData data) {
        this.data = data;
    }

    public static class GameValueData {
        private String type;
        private String target;

        public GameValueData() {
        }

        public GameValueData(String type, String target) {
            this.type = type;
            this.target = target;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTarget() {
            return this.target;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }

}
