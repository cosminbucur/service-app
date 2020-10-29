package com.fm.dto.request;

import java.util.ArrayList;
import java.util.List;

public class StoragePointWrite {

    private String code;
    private int numberOfRimCaps;
    private String licensePlate;

    private List<TyreWrite> mountedTyres = new ArrayList<>();
    private List<TyreWrite> storedTyres = new ArrayList<>();

    public StoragePointWrite() {
    }

    public void removeTyres(List<TyreWrite> list) {
        storedTyres.removeAll(list);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumberOfRimCaps() {
        return numberOfRimCaps;
    }

    public void setNumberOfRimCaps(int numberOfRimCaps) {
        this.numberOfRimCaps = numberOfRimCaps;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<TyreWrite> getMountedTyres() {
        return mountedTyres;
    }

    public void setMountedTyres(List<TyreWrite> mountedTyres) {
        this.mountedTyres = mountedTyres;
    }

    public List<TyreWrite> getStoredTyres() {
        return storedTyres;
    }

    public void setStoredTyres(List<TyreWrite> storedTyres) {
        this.storedTyres = storedTyres;
    }
}
