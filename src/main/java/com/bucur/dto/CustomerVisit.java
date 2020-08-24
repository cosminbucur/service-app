package com.bucur.dto;

import java.time.LocalDate;

public class CustomerVisit {

    public long customerId;
    public LocalDate visitDate;
    public String licensePlate;
    public boolean seasonPassed;

    public CustomerVisit(long customerId, LocalDate visitDate, String licensePlate) {
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.licensePlate = licensePlate;
        this.seasonPassed = false;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }
}
