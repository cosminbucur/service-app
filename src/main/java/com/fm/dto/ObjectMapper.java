package com.fm.dto;

import com.fm.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {


    // customer visit - entity
    public CustomerVisit toEntity(ServiceInfo serviceInfo) {
        CustomerVisit customerVisit = new CustomerVisit();
        customerVisit.setVisitDate(serviceInfo.getVisitDate());
        customerVisit.setMechanicId(serviceInfo.getMechanicId());
        customerVisit.setServicesPerformed(serviceInfo.getServicesPerformed());
        customerVisit.setObservations(serviceInfo.getObservations());

        return customerVisit;
    }

    public CustomerVisit toEntity(CustomerVisitInfo customerVisitInfo) {
        Customer customer = getCustomerFromCustomerVisitInfo(customerVisitInfo);

        CustomerVisit customerVisit = new CustomerVisit();
        customerVisit.setVisitDate(customerVisitInfo.getServiceInfo().getVisitDate());
        customerVisit.setMechanicId(customerVisitInfo.getServiceInfo().getMechanicId());
        customerVisit.setServicesPerformed(customerVisitInfo.getServiceInfo().getServicesPerformed());
        customerVisit.setObservations(customerVisitInfo.getServiceInfo().getObservations());
        customerVisit.setCustomer(customer);


        return customerVisit;
    }

    public CustomerVisit toDto(CustomerVisit newCustomerVisit) {
        CustomerVisit customerVisitToDto = new CustomerVisit();

        customerVisitToDto.setId(newCustomerVisit.getId());
        customerVisitToDto.setVisitDate(newCustomerVisit.getVisitDate());
        customerVisitToDto.setMechanicId(newCustomerVisit.getMechanicId());
        customerVisitToDto.setObservations(newCustomerVisit.getObservations());
        customerVisitToDto.setObservations(newCustomerVisit.getObservations());
        customerVisitToDto.setCustomer(newCustomerVisit.getCustomer());

        return customerVisitToDto;

    }

    private Customer getCustomerFromCustomerVisitInfo(CustomerVisitInfo customerVisitInfo) {
        Customer customer = new Customer();
        customer.setId(customerVisitInfo.getCustomerInfo().getId());
        customer.setFirstName(customerVisitInfo.getCustomerInfo().getFirstName());
        customer.setLastName(customerVisitInfo.getCustomerInfo().getLastName());
        customer.setCompany(customerVisitInfo.getCustomerInfo().getCompany());
        customer.setEmail(customerVisitInfo.getCustomerInfo().getEmail());
        customer.setPhoneNumber(customerVisitInfo.getCustomerInfo().getPhoneNumber());
        return customer;
    }

    // storage point

    public StoragePoint toEntity(StoragePointInfo dto) {
        StoragePoint entity = new StoragePoint();
        entity.setLicensePlate(dto.getLicensePlate());
        entity.setNumberOfRimCaps(dto.getNumberOfRimCaps());
        entity.setMountedTyres(toEntity(dto.getStoredTyres()));
        entity.setStoredTyres(toEntity(dto.getStoredTyres()));
        return entity;
    }

    public StoragePointInfo toDto(StoragePoint entity) {
        StoragePointInfo storagePointInfo = new StoragePointInfo();
        storagePointInfo.setId(entity.getId());
        storagePointInfo.setCode(entity.getCode());
        storagePointInfo.setNumberOfRimCaps(entity.getNumberOfRimCaps());
        storagePointInfo.setLicensePlate(entity.getLicensePlate());

        storagePointInfo.setMountedTyres(toDto(entity.getMountedTyres()));
        storagePointInfo.setStoredTyres(toDto(entity.getStoredTyres()));
        return storagePointInfo;
    }

    // customer

    public Customer toEntity(CustomerInfo customerInfo) {
        Customer customer = new Customer();
        customer.setFirstName(customerInfo.getFirstName());
        customer.setLastName(customerInfo.getLastName());
        customer.setCompany(customerInfo.getCompany());
        customer.setEmail(customerInfo.getEmail());
        customer.setPhoneNumber(customerInfo.getPhoneNumber());
        customer.addLicensePlate(customerInfo.getPhoneNumber());
        return customer;
    }

    // tyres - entity

    public List<Tyre> toEntity(List<TyreInfo> dtos) {
        return dtos.stream().map(this::toEntity)
            .collect(Collectors.toList());
    }

    public Tyre toEntity(TyreInfo dto) {
        Tyre entity = new Tyre(
            dto.getId(),
            dto.getTyreBrand(),
            toEntity(dto.getTyreSize()),
            dto.getTyreType(),
            dto.getRimType(),
            dto.getTreadDepth()
        );

        // TODO: fix this
        entity.setLicensePlate("");
        return entity;
    }

    public TyreSize toEntity(TyreSizeInfo dto) {
        return new TyreSize(dto.getTyreHeight(), dto.getTyreWidth(), dto.getRimDiameter());
    }

    // tyres - dto

    public List<TyreInfo> toDto(List<Tyre> entities) {
        return entities.stream().map(this::toDto)
            .collect(Collectors.toList());
    }

    public TyreInfo toDto(Tyre entity) {
        TyreInfo dto = new TyreInfo();
        dto.setId(entity.getId());
        dto.setTyreType(entity.getTyreType());
        dto.setTyreBrand(entity.getTyreBrand());

        dto.setTyreSize(toDto(entity.getTyreSize()));

        dto.setWearLevel(entity.getWearLevel());
        dto.setRimType(entity.getRimType());
        return dto;
    }

    public TyreSizeInfo toDto(TyreSize dto) {
        return new TyreSizeInfo(dto.getTyreHeight(), dto.getTyreWidth(), dto.getRimDiameter());
    }
}
