package com.bucur.dto;

public class Vehicle {

    public String licensePlate;
    public Tyre frontLeft;
    public Tyre rearLeft;
    public Tyre frontRight;
    public Tyre rearRight;

    private Vehicle() {
    }

    public static class Builder {
        private String licensePlate;
        private Tyre frontLeft;
        private Tyre rearLeft;
        private Tyre frontRight;
        private Tyre rearRight;

        public Builder withLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder withFrontLeft(Tyre frontLeft) {
            this.frontLeft = frontLeft;
            return this;
        }

        public Builder withRearLeft(Tyre rearLeft) {
            this.rearLeft = rearLeft;
            return this;
        }

        public Builder withFrontRight(Tyre frontRight) {
            this.frontRight = frontRight;
            return this;
        }

        public Builder withRearRight(Tyre rearRight) {
            this.rearRight = rearRight;
            return this;
        }

        public Vehicle build() {
            Vehicle vehicle = new Vehicle();
            vehicle.frontLeft = this.frontLeft;
            vehicle.frontRight = this.frontRight;
            vehicle.rearLeft = this.rearLeft;
            vehicle.rearRight = this.rearRight;
            return vehicle;
        }

        public Tyre getFrontLeft() {
            return frontLeft;
        }

        public void setFrontLeft(Tyre frontLeft) {
            this.frontLeft = frontLeft;
        }

        public Tyre getRearLeft() {
            return rearLeft;
        }

        public void setRearLeft(Tyre rearLeft) {
            this.rearLeft = rearLeft;
        }

        public Tyre getFrontRight() {
            return frontRight;
        }

        public void setFrontRight(Tyre frontRight) {
            this.frontRight = frontRight;
        }

        public Tyre getRearRight() {
            return rearRight;
        }

        public void setRearRight(Tyre rearRight) {
            this.rearRight = rearRight;
        }
    }
}
