package com.bucur.model;

import java.util.List;

public class StoragePoint {

    public String licensePlate;

    private List<Tyre> tyres;

    public List<Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
    }
}
