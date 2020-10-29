package com.fm.model;

public class TyreSize {

    private int tyreWidth;
    private int tyreHeight;
    private int rimDiameter;

    public TyreSize() {
    }

    public TyreSize(int tyreWidth, int tyreHeight, int rimDiameter) {
        this.tyreWidth = tyreWidth;
        this.tyreHeight = tyreHeight;
        this.rimDiameter = rimDiameter;
    }

    public int getTyreWidth() {
        return tyreWidth;
    }

    public void setTyreWidth(int tyreWidth) {
        this.tyreWidth = tyreWidth;
    }

    public int getTyreHeight() {
        return tyreHeight;
    }

    public void setTyreHeight(int tyreHeight) {
        this.tyreHeight = tyreHeight;
    }

    public int getRimDiameter() {
        return rimDiameter;
    }

    public void setRimDiameter(int rimDiameter) {
        this.rimDiameter = rimDiameter;
    }
}
