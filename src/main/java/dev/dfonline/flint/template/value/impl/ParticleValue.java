package dev.dfonline.flint.template.value.impl;

import dev.dfonline.flint.template.value.ParticleSetting;
import dev.dfonline.flint.template.value.Value;

import java.util.HashMap;
import java.util.Map;

public class ParticleValue extends Value {

    private ParticleData data;

    public ParticleValue() {
        this.setId("part");
    }

    public ParticleData getData() {
        return this.data;
    }

    public void setData(ParticleData data) {
        this.data = data;
    }

    public static class ParticleData {
        private String particle;
        private ClusterData cluster;
        private Map<String, Object> data = new HashMap<>();

        public String getParticle() {
            return this.particle;
        }

        public void setParticle(String particle) {
            this.particle = particle;
        }

        public ClusterData getCluster() {
            return this.cluster;
        }

        public void setCluster(ClusterData cluster) {
            this.cluster = cluster;
        }

        public Map<String, Object> getData() {
            return this.data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }

        public void setSetting(ParticleSetting setting, Object value) {
            this.data.put(setting.getKey(), value);
        }

        public Object getSetting(ParticleSetting setting) {
            return this.data.get(setting.getKey());
        }
    }

    public static class ClusterData {
        private int amount;
        private double horizontal;
        private double vertical;

        public int getAmount() {
            return this.amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getHorizontal() {
            return this.horizontal;
        }

        public void setHorizontal(double horizontal) {
            this.horizontal = horizontal;
        }

        public double getVertical() {
            return this.vertical;
        }

        public void setVertical(double vertical) {
            this.vertical = vertical;
        }
    }

}
