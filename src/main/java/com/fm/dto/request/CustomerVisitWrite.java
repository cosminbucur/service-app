package com.fm.dto.request;

public class CustomerVisitWrite {

    // if customer id is set = existing customer
    // else customer data will be set
    private Long customerIdMaybe;
    private CustomerWrite customerWriteMaybe;

    private ServiceWrite serviceWrite;
    private StoragePointWrite storagePointWrite;

    public Long getCustomerIdMaybe() {
        return customerIdMaybe;
    }

    public void setCustomerIdMaybe(Long customerIdMaybe) {
        this.customerIdMaybe = customerIdMaybe;
    }

    public CustomerWrite getCustomerInfo() {
        return customerWriteMaybe;
    }

    public void setCustomerInfo(CustomerWrite customerWrite) {
        this.customerWriteMaybe = customerWrite;
    }

    public ServiceWrite getServiceInfo() {
        return serviceWrite;
    }

    public void setServiceInfo(ServiceWrite serviceWrite) {
        this.serviceWrite = serviceWrite;
    }

    public StoragePointWrite getStoragePointInfo() {
        return storagePointWrite;
    }

    public void setStoragePointInfo(StoragePointWrite storagePointWrite) {
        this.storagePointWrite = storagePointWrite;
    }

    public boolean isSeasonPassed() {
        return false;
    }
}
