package com.fm.dto;

import java.util.ArrayList;
import java.util.List;

public class StoragePointDetail {

    private String licensePlate;
    private int numberOfRimCaps;

    private List<TyreDetail> tyreList = new ArrayList<>();

    public StoragePointDetail() {
    }

    public void removeTyres(List<TyreDetail> list) {
        tyreList.removeAll(list);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumberOfRimCaps() {
        return numberOfRimCaps;
    }

    public void setNumberOfRimCaps(int numberOfRimCaps) {
        this.numberOfRimCaps = numberOfRimCaps;
    }

    public List<TyreDetail> getTyreList() {
        return tyreList;
    }

    public void setTyreList(List<TyreDetail> tyreList) {
        this.tyreList = tyreList;
    }
}
