package com.bucur.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerVisit {

    public final long customerId;
    public final LocalDate visitDate;
    public final List<String> licensePlates;
    public boolean seasonPassed;

    public CustomerVisit(long customerId, LocalDate visitDate, List<String> licensePlates) {
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.licensePlates = licensePlates;
        this.seasonPassed = false;
    }
}
