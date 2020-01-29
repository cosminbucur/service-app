package com.bucur.dto;

import com.bucur.model.RimType;
import com.bucur.model.TyreType;
import com.bucur.model.Wear;

import java.util.Objects;

public class Tyre {

    public String tyreBrand;
    public int tyreWidth;
    public int tyreHeight;
    public String rimDiameter;
    public TyreType type;
    public RimType rimType;
    public int numberOfRimCaps;
    public int treadDepth;
    public Wear wear;
    public Long storageId;
    public String licensePlate;

    public Tyre() {

    }

    public Tyre(String tyreBrand, TyreType type) {
        this.tyreBrand = tyreBrand;
        this.type = type;
    }

    public Tyre(String tyreBrand, TyreType type, int treadDepth, int tyreWidth, int tyreHeight, String rimDiameter) {
        this.tyreBrand = tyreBrand;
        this.type = type;
        setTyreWearLevel(treadDepth);
        setTyreSize(tyreWidth, tyreHeight, rimDiameter);
    }

    private String setTyreSize(int tyreWidth, int tyreHeight, String rimDiameter) {
        return tyreWidth + "/" + tyreHeight + rimDiameter;
    }

    private void setTyreWearLevel(int threadDepth) {
        if (threadDepth <= 0 || threadDepth > 8) {
            throw new IllegalArgumentException("must be between 1-8");
        }

        if (threadDepth == 2) {
            this.wear = Wear.DANGER;
        }
        if (threadDepth == 3) {
            this.wear = Wear.WARNING;
        }
        if (isInRangeIncluding(threadDepth, 4, 5)) {
            this.wear = Wear.OK;
        }
        if (isInRangeIncluding(threadDepth, 6, 8)) {
            this.wear = Wear.GOOD;
        }
    }

    private boolean isInRangeIncluding(int number, int min, int max) {
        return number >= min && number <= max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tyre tyre = (Tyre) o;
        return treadDepth == tyre.treadDepth &&
                Objects.equals(tyreBrand, tyre.tyreBrand) &&
                type == tyre.type &&
                wear == tyre.wear &&
                Objects.equals(storageId, tyre.storageId) &&
                Objects.equals(licensePlate, tyre.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tyreBrand, type, treadDepth, wear, storageId, licensePlate);
    }

    @Override
    public String
    toString() {
        return "Tyre{" +
                "tyreBrand='" + tyreBrand + '\'' +
                ", type=" + type +
                ", threadDepth=" + treadDepth +
                ", wear=" + wear +
                ", storageId=" + storageId +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }

}
