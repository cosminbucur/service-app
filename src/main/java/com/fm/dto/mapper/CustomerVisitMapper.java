package com.fm.dto.mapper;

import com.fm.dto.request.ServiceWrite;
import com.fm.dto.response.CustomerVisitRead;
import com.fm.model.CustomerVisit;

public class CustomerVisitMapper {

    private CustomerVisitMapper() {
    }

    public static CustomerVisit toEntity(ServiceWrite serviceWrite) {
        CustomerVisit entity = new CustomerVisit();
        entity.setMechanicId(serviceWrite.getMechanicId());
        entity.setServicesPerformed(serviceWrite.getServicesPerformed());
        entity.setObservations(serviceWrite.getObservations());

        return entity;
    }

    public static CustomerVisitRead toDto(CustomerVisit entity) {
        CustomerVisitRead dto = new CustomerVisitRead();

        dto.setId(entity.getId());
        dto.setCustomerRead(CustomerMapper.toDto(entity.getCustomer()));
        dto.setServiceRead(null);
        dto.setStoragePointRead(null);

        return dto;
    }

}
