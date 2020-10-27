package com.fm.dto;

import com.fm.model.RimType;
import com.fm.model.Season;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;

public class TyreInfo {

    private Long id;
    private String tyreBrand;
    private TyreSizeInfo tyreSizeInfo;
    private TyreType tyreType;
    private Season season;
    private RimType rimType;
    private int treadDepth;
    private WearLevel wearLevel;

    public TyreInfo() {
    }

    public TyreInfo(Long id, String tyreBrand, TyreSizeInfo tyreSizeInfo, TyreType tyreType, Season season, RimType rimType, int treadDepth) {
        this.id = id;
        this.tyreBrand = tyreBrand;
        this.tyreSizeInfo = tyreSizeInfo;
        this.tyreType = tyreType;
        this.season = season;
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

    public TyreSizeInfo getTyreSizeInfo() {
        return tyreSizeInfo;
    }

    public void setTyreSizeInfo(TyreSizeInfo tyreSizeInfo) {
        this.tyreSizeInfo = tyreSizeInfo;
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
}
