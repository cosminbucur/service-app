package com.fm.dto;

import java.util.List;

// TODO: remove dto (but save builder example)
public class Vehicle {

    public String licensePlate;
    public TyreDetail frontLeft;
    public TyreDetail rearLeft;
    public TyreDetail frontRight;
    public TyreDetail rearRight;

    public Vehicle() {
    }

    public void mountTyres(List<TyreDetail> newTyreDetails) {
        newTyreDetails.add(frontLeft);
        newTyreDetails.add(frontRight);
        newTyreDetails.add(rearLeft);
        newTyreDetails.add(rearRight);
    }

    public static class Builder {
        private String licensePlate;
        private TyreDetail frontLeft;
        private TyreDetail rearLeft;
        private TyreDetail frontRight;
        private TyreDetail rearRight;

        public Builder withLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder withFrontLeft(TyreDetail frontLeft) {
            this.frontLeft = frontLeft;
            return this;
        }

        public Builder withRearLeft(TyreDetail rearLeft) {
            this.rearLeft = rearLeft;
            return this;
        }

        public Builder withFrontRight(TyreDetail frontRight) {
            this.frontRight = frontRight;
            return this;
        }

        public Builder withRearRight(TyreDetail rearRight) {
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

        public TyreDetail getFrontLeft() {
            return frontLeft;
        }

        public void setFrontLeft(TyreDetail frontLeft) {
            this.frontLeft = frontLeft;
        }

        public TyreDetail getRearLeft() {
            return rearLeft;
        }

        public void setRearLeft(TyreDetail rearLeft) {
            this.rearLeft = rearLeft;
        }

        public TyreDetail getFrontRight() {
            return frontRight;
        }

        public void setFrontRight(TyreDetail frontRight) {
            this.frontRight = frontRight;
        }

        public TyreDetail getRearRight() {
            return rearRight;
        }

        public void setRearRight(TyreDetail rearRight) {
            this.rearRight = rearRight;
        }
    }
}
