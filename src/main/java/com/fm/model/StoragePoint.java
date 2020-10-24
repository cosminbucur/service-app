package com.fm.model;

import java.util.ArrayList;
import java.util.List;

public class StoragePoint {

    private String licensePlate;

    private List<Tyre> tyres = new ArrayList<>();

    public StoragePoint() {
    }

    public void removeTyres(List<Tyre> list) {
        tyres.removeAll(list);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Tyre> getTyreDetails() {
        return tyres;
    }

    public void setTyreDetails(List<Tyre> tyres) {
        this.tyres = tyres;
    }
}
