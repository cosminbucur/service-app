package com.fm.dto;

import java.time.LocalDate;

public class CustomerVisit {

    private long customerId;
    private LocalDate visitDate;
    private String licensePlate;
    private boolean seasonPassed;

    public CustomerVisit(long customerId, LocalDate visitDate, String licensePlate) {
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.licensePlate = licensePlate;
        this.seasonPassed = false;
    }

    // FIXME don't add useless empty rows
    // convention is 1 empty row between stuff

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isSeasonPassed() {
        return seasonPassed;
    }

    public void setSeasonPassed(boolean seasonPassed) {
        this.seasonPassed = seasonPassed;
    }
}
