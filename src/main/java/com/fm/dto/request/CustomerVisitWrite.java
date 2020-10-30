package com.fm.dto.request;

public class CustomerVisitWrite {

    // if customer id is set = existing customer
    // else customer data will be set
    private Long customerIdMaybe;
    private CustomerWrite customerWriteMaybe;

    private Long mechanicId;
    private String servicesPerformed;
    private String observations;

    private StoragePointWrite storagePointWrite;

    public Long getCustomerIdMaybe() {
        return customerIdMaybe;
    }

    public void setCustomerIdMaybe(Long customerIdMaybe) {
        this.customerIdMaybe = customerIdMaybe;
    }

    public CustomerWrite getCustomerWriteMaybe() {
        return customerWriteMaybe;
    }

    public void setCustomerWriteMaybe(CustomerWrite customerWriteMaybe) {
        this.customerWriteMaybe = customerWriteMaybe;
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

    public StoragePointWrite getStoragePointWrite() {
        return storagePointWrite;
    }

    public void setStoragePointWrite(StoragePointWrite storagePointWrite) {
        this.storagePointWrite = storagePointWrite;
    }

}
