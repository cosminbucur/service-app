package com.fm.dto;

import java.time.LocalDate;

public class CustomerVisitDetails {

    private LocalDate visitDate;
    private CustomerDetail customerDetail;

    public CustomerVisitDetails() {
    }

    public CustomerVisitDetails(int i, LocalDate now, String b22ABC) {

    }

    public CustomerDetail getCustomerDetails() {
        return customerDetail;
    }

    public void setCustomerDetails(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public boolean isSeasonPassed() {
        return false;
    }
}
