package com.bucur.dto;

import java.util.ArrayList;
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
}
