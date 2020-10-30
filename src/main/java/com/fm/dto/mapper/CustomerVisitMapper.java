package com.fm.dto.mapper;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.response.CustomerVisitRead;
import com.fm.model.CustomerVisit;

public class CustomerVisitMapper {

    private CustomerVisitMapper() {
    }

    public static CustomerVisit toEntity(CustomerVisitWrite dto) {
        CustomerVisit entity = new CustomerVisit();
        entity.setMechanicId(dto.getMechanicId());
        entity.setServicesPerformed(dto.getServicesPerformed());
        entity.setObservations(dto.getObservations());

        return entity;
    }

    public static CustomerVisitRead toDto(CustomerVisit entity) {
        CustomerVisitRead dto = new CustomerVisitRead();
        dto.setId(entity.getId());
        dto.setVisitDate(entity.getVisitDate());
        dto.setMechanicId(entity.getMechanicId());
        dto.setServicesPerformed(entity.getServicesPerformed());
        dto.setObservations(entity.getObservations());
        dto.setCustomerRead(CustomerMapper.toDto(entity.getCustomer()));
        dto.setStoragePointRead(null);

        return dto;
    }

}
