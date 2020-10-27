package com.fm.dto;

public class CustomerVisitInfo {

    private CustomerInfo customerInfo;
    private ServiceInfo serviceInfo;
    private StoragePointInfo storagePointInfo;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public StoragePointInfo getStoragePointInfo() {
        return storagePointInfo;
    }

    public void setStoragePointInfo(StoragePointInfo storagePointInfo) {
        this.storagePointInfo = storagePointInfo;
    }

    public boolean isSeasonPassed() {
        return false;
    }
}
