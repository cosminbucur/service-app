package com.fm.dto.response;

public class CustomerVisitRead {

    private Long id;
    private CustomerRead customerRead;
    private ServiceRead serviceRead;
    private StoragePointRead storagePointRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerRead getCustomerRead() {
        return customerRead;
    }

    public void setCustomerRead(CustomerRead customerRead) {
        this.customerRead = customerRead;
    }

    public ServiceRead getServiceInfo() {
        return serviceRead;
    }

    public void setServiceInfo(ServiceRead serviceRead) {
        this.serviceRead = serviceRead;
    }

    public StoragePointRead getStoragePointInfo() {
        return storagePointRead;
    }

    public void setStoragePointInfo(StoragePointRead storagePointRead) {
        this.storagePointRead = storagePointRead;
    }

    public boolean isSeasonPassed() {
        return false;
    }
}
