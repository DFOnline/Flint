package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.dfonline.flint.template.value.Value;

public class LocationValue extends Value {

    private LocationData data;

    public LocationValue() {
        this.setId("loc");
    }

    public LocationValue(double x, double y, double z) {
        this();
        this.data = new LocationData(false, new LocationCoordinates(x, y, z, 0, 0));
    }

    public LocationValue(double x, double y, double z, float pitch, float yaw) {
        this();
        this.data = new LocationData(false, new LocationCoordinates(x, y, z, pitch, yaw));
    }

    public LocationData getData() {
        return this.data;
    }

    public void setData(LocationData data) {
        this.data = data;
    }

    public static class LocationData {
        @JsonProperty
        private boolean isBlock = false;
        private LocationCoordinates loc;

        public LocationData() {
        }

        public LocationData(boolean isBlock, LocationCoordinates loc) {
            this.isBlock = isBlock;
            this.loc = loc;
        }

        public LocationCoordinates getLoc() {
            return this.loc;
        }

        public void setLoc(LocationCoordinates loc) {
            this.loc = loc;
        }
    }

    public static class LocationCoordinates {
        private double x;
        private double y;
        private double z;
        private float pitch;
        private float yaw;

        public LocationCoordinates() {
        }

        public LocationCoordinates(double x, double y, double z, float pitch, float yaw) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.pitch = pitch;
            this.yaw = yaw;
        }

        public double getX() {
            return this.x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return this.y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getZ() {
            return this.z;
        }

        public void setZ(double z) {
            this.z = z;
        }

        public float getPitch() {
            return this.pitch;
        }

        public void setPitch(float pitch) {
            this.pitch = pitch;
        }

        public float getYaw() {
            return this.yaw;
        }

        public void setYaw(float yaw) {
            this.yaw = yaw;
        }
    }

}

