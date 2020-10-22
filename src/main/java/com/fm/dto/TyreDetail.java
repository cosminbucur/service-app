package com.fm.dto;

import com.fm.model.RimType;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;

public class TyreDetail {

    private Long id;
    private String tyreBrand;
    private TyreSize tyreSize;
    private TyreType tyreType;
    private RimType rimType;
    private int treadDepth;
    private WearLevel wearLevel;

    public TyreDetail() {
    }

    public TyreDetail(Long id, String tyreBrand, TyreSize tyreSize, TyreType tyreType, RimType rimType, int treadDepth) {
        this.id = id;
        this.tyreBrand = tyreBrand;
        this.tyreSize = tyreSize;
        this.tyreType = tyreType;
        this.rimType = rimType;
        this.treadDepth = treadDepth;
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
}
