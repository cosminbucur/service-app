package com.fm.dto.request;

// TODO: consider options
// 1. move fields to customer visit
// 2. leave it and modify mapper
// 3. modify customer visit with embedded service object
public class ServiceWrite {

    private Long mechanicId;
    private String servicesPerformed;
    private String observations;

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
