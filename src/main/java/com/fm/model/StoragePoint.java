package com.fm.model;

import java.util.ArrayList;
import java.util.List;

public class StoragePoint {

    private Long id;
    private String code;
    private String licensePlate;
    private int numberOfRimCaps;

    private List<Tyre> mountedTyres = new ArrayList<>();
    private List<Tyre> storedTyres = new ArrayList<>();

    public StoragePoint() {
    }

    public void clear() {
        numberOfRimCaps = 0;
        mountedTyres.clear();
        storedTyres.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public List<Tyre> getMountedTyres() {
        return mountedTyres;
    }

    public void setMountedTyres(List<Tyre> mountedTyres) {
        this.mountedTyres = mountedTyres;
    }

    public List<Tyre> getStoredTyres() {
        return storedTyres;
    }

    public void setStoredTyres(List<Tyre> storedTyres) {
        this.storedTyres = storedTyres;
    }
}
