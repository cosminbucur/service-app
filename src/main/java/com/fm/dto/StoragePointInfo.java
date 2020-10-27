package com.fm.dto;

import java.util.ArrayList;
import java.util.List;

public class StoragePointInfo {

    private Long id;
    private String code;
    private String licensePlate;
    private int numberOfRimCaps;

    // TODO: use enum (MOUNTED, STORED)  and reduce to a single list
    private List<TyreInfo> mountedTyres = new ArrayList<>();
    private List<TyreInfo> storedTyres = new ArrayList<>();

    public StoragePointInfo() {
    }

    public void removeTyres(List<TyreInfo> list) {
        storedTyres.removeAll(list);
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

    public List<TyreInfo> getMountedTyres() {
        return mountedTyres;
    }

    public void setMountedTyres(List<TyreInfo> mountedTyres) {
        this.mountedTyres = mountedTyres;
    }

    public List<TyreInfo> getStoredTyres() {
        return storedTyres;
    }

    public void setStoredTyres(List<TyreInfo> storedTyres) {
        this.storedTyres = storedTyres;
    }
}
