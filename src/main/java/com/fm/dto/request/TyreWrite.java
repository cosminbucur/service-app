package com.fm.dto.request;

import com.fm.model.RimType;
import com.fm.model.Season;
import com.fm.model.TyreLocation;
import com.fm.model.TyreSize;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;

public class TyreWrite {

    private String tyreBrand;
    private TyreSize tyreSize;
    private TyreType tyreType;
    private Season season;
    private RimType rimType;
    private int treadDepth;
    private WearLevel wearLevel;
    private TyreLocation tyreLocation;

    public TyreWrite() {
    }

    public TyreWrite(String tyreBrand, TyreSize tyreSize, TyreType tyreType, Season season, RimType rimType, int treadDepth, TyreLocation tyreLocation) {
        this.tyreBrand = tyreBrand;
        this.tyreSize = tyreSize;
        this.tyreType = tyreType;
        this.season = season;
        this.rimType = rimType;
        this.treadDepth = treadDepth;
        this.tyreLocation = tyreLocation;
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

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
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

    public TyreLocation getTyreLocation() {
        return tyreLocation;
    }

    public void setTyreLocation(TyreLocation tyreLocation) {
        this.tyreLocation = tyreLocation;
    }
}
