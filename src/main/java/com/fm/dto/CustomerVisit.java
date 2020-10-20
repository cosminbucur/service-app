package com.fm.dto;

import java.time.LocalDate;

public class CustomerVisit {

    private CustomerDetails customerDetails;

    private LocalDate visitDate;
    private boolean seasonPassed;

    public CustomerVisit() {
    }

    public CustomerVisit(int i, LocalDate now, String b22ABC) {

    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public boolean isSeasonPassed() {
        return seasonPassed;
    }

    public void setSeasonPassed(boolean seasonPassed) {
        this.seasonPassed = seasonPassed;
    }
}
