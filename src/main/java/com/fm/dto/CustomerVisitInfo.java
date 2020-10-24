package com.fm.dto;

public class CustomerVisitInfo {

    private CustomerInfo customerInfo;
    private ServiceInfo serviceInfo;

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

    public boolean isSeasonPassed() {
        return false;
    }
}
