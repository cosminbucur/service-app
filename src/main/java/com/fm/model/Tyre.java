package com.fm.model;

public class Tyre {

    private Long id;
    private String tyreBrand;
    private TyreSize tyreSize;
    private TyreType tyreType;
    private RimType rimType;
    private int treadDepth;
    private WearLevel wearLevel;

    private Long storageId;
    private String licensePlate;

    public Tyre() {
    }

    public Tyre(Long id, String tyreBrand, TyreSize tyreSize, TyreType tyreType, RimType rimType, int treadDepth) {
        this.id = id;
        this.tyreBrand = tyreBrand;
        this.tyreSize = tyreSize;
        this.tyreType = tyreType;
        this.rimType = rimType;
        this.treadDepth = treadDepth;
        setTyreWearLevel(treadDepth);
    }

    private void setTyreWearLevel(int threadDepth) {
        if (threadDepth <= 0 || threadDepth > 8) {
            throw new IllegalArgumentException("must be between 1-8");
        }

        if (threadDepth == 2) {
            this.wearLevel = WearLevel.DANGER;
        }
        if (threadDepth == 3) {
            this.wearLevel = WearLevel.WARNING;
        }
        if (isInRangeIncluding(threadDepth, 4, 5)) {
            this.wearLevel = WearLevel.OK;
        }
        if (isInRangeIncluding(threadDepth, 6, 8)) {
            this.wearLevel = WearLevel.GOOD;
        }
    }

    private boolean isInRangeIncluding(int number, int min, int max) {
        return number >= min && number <= max;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTyreBrand() {
        return tyreBrand;
    }

    public void setTyreBrand(String tyreBrand) {
        this.tyreBrand = tyreBrand;
    }

    public TyreSize getTyreSize() {
        return tyreSize;
    }

    public void setTyreSize(TyreSize tyreSize) {
        this.tyreSize = tyreSize;
    }

    public TyreType getTyreType() {
        return tyreType;
    }

    public void setTyreType(TyreType tyreType) {
        this.tyreType = tyreType;
    }

    public RimType getRimType() {
        return rimType;
    }

    public void setRimType(RimType rimType) {
        this.rimType = rimType;
    }

    public int getTreadDepth() {
        return treadDepth;
    }

    public void setTreadDepth(int treadDepth) {
        this.treadDepth = treadDepth;
    }

    public WearLevel getWearLevel() {
        return wearLevel;
    }

    public void setWearLevel(WearLevel wearLevel) {
        this.wearLevel = wearLevel;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}