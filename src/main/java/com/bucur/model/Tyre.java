package com.bucur.model;

public class Tyre {

    public String tyreBrand;
    public TyreType type;
    public int wear;
    public Long storageId;
    public String licensePlate;

    public Tyre() {
    }

//        public Tyre(String tyreBrand, TyreType type) {
//        this.tyreBrand = tyreBrand;
//        this.type = type;
//    }
//
public Tyre(String tyreBrand, TyreType type, int wear) {
    this.tyreBrand = tyreBrand;
    this.type = type;
    this.wear = wear;
}

}
