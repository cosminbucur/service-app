package com.fm.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoragePoint {

    public String licensePlate;

    private List<Tyre> tyres = new ArrayList<>();

    public List<Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void removeTyres(List<Tyre> tyres) {
        // TODO: remove the selected tyres
        for (Tyre tyre : tyres) {
            setTyres(Collections.EMPTY_LIST);
        }
    }
}
