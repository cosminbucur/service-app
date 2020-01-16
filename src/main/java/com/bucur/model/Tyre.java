package com.bucur.model;

public class Tyre {

    public String tyreBrand;
    public TyreType type;
    public int wear;


    public Tyre(String tyreBrand, TyreType type) {
        this.tyreBrand = tyreBrand;
        this.type = type;
    }

    public Tyre() {

    }

    public Tyre(int wear) {
        this.wear = wear;
    }
}
