package com.fm.model;

import java.time.LocalDate;

public class CustomerVisit {

    private Long id;
    private LocalDate visitDate;
    private Long mechanicId;
    private String servicesPerformed;
    private String observations;

    public CustomerVisit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Long getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(Long mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getServicesPerformed() {
        return servicesPerformed;
    }

    public void setServicesPerformed(String servicesPerformed) {
        this.servicesPerformed = servicesPerformed;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
