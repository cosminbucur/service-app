package com.fm.dto.response;

import java.time.LocalDate;

public class CustomerVisitRead {

    private Long id;
    private LocalDate visitDate;
    private Long mechanicId;
    private String servicesPerformed;
    private String observations;
    private CustomerRead customerRead;
    private StoragePointRead storagePointRead;

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

    public CustomerRead getCustomerRead() {
        return customerRead;
    }

    public void setCustomerRead(CustomerRead customerRead) {
        this.customerRead = customerRead;
    }

    public StoragePointRead getStoragePointRead() {
        return storagePointRead;
    }

    public void setStoragePointRead(StoragePointRead storagePointRead) {
        this.storagePointRead = storagePointRead;
    }

}
