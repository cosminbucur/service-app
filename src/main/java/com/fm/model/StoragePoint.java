package com.fm.model;

import com.fm.dto.TyreDetail;

import java.util.ArrayList;
import java.util.List;

public class StoragePoint {

    private String licensePlate;

    private List<TyreDetail> tyreDetails = new ArrayList<>();

    public StoragePoint() {
    }

    public void removeTyres(List<TyreDetail> list) {
        tyreDetails.removeAll(list);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<TyreDetail> getTyreDetails() {
        return tyreDetails;
    }

    public void setTyreDetails(List<TyreDetail> tyreDetails) {
        this.tyreDetails = tyreDetails;
    }
}
