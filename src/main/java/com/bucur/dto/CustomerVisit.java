package com.bucur.dto;

import java.time.LocalDate;

public class CustomerVisit {

    public long customerId;
    public LocalDate visitDate;
    public String licensePlates;
    public boolean seasonPassed;

    public CustomerVisit(long customerId, LocalDate visitDate, String licensePlates) {
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.licensePlates = licensePlates;
        this.seasonPassed = false;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

}
