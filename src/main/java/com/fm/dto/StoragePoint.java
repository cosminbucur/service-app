package com.fm.dto;

import com.fm.model.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoragePoint {

    public String licensePlate;

    private List<Tyre> tyres = new ArrayList<>();

    // FIXME why do you need a customer on the storage point?
    private Customer customer;

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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
