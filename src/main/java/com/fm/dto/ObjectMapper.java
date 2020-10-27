package com.fm.dto;

import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.model.StoragePoint;
import com.fm.model.Tyre;
import com.fm.model.TyreSize;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    // customer visit

    public CustomerVisit toEntity(ServiceInfo serviceInfo) {
        CustomerVisit customerVisit = new CustomerVisit();
        customerVisit.setVisitDate(serviceInfo.getVisitDate());
        customerVisit.setMechanicId(serviceInfo.getMechanicId());
        customerVisit.setServicesPerformed(serviceInfo.getServicesPerformed());
        customerVisit.setObservations(serviceInfo.getObservations());

        return customerVisit;
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
