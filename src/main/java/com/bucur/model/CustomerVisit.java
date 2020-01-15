package com.bucur.model;

import java.time.LocalDate;
import java.util.List;

public class CustomerVisit {

    public final long id;
    public final long customerId;
    public final LocalDate visitDate;
    public final List<String> licensePlates;

    public CustomerVisit(long id, long customerId, LocalDate visitDate, List<String> licensePlates) {
        this.id = id;
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.licensePlates = licensePlates;
    }
}
