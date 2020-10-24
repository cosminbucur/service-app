package com.fm.dto;

import java.util.ArrayList;
import java.util.List;

public class StoragePointInfo {

    private String licensePlate;
    private int numberOfRimCaps;

    private List<TyreInfo> mountedTyres = new ArrayList<>();
    private List<TyreInfo> storageTyres = new ArrayList<>();

    public StoragePointInfo() {
    }

    public void removeTyres(List<TyreInfo> list) {
        mountedTyres.removeAll(list);
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

    public List<TyreInfo> getMountedTyres() {
        return mountedTyres;
    }

    public void setMountedTyres(List<TyreInfo> mountedTyres) {
        this.mountedTyres = mountedTyres;
    }

    public List<TyreInfo> getStorageTyres() {
        return storageTyres;
    }

    public void setStorageTyres(List<TyreInfo> storageTyres) {
        this.storageTyres = storageTyres;
    }
}
