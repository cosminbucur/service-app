package com.fm.dto.mapper;

import com.fm.dto.request.ServiceWrite;
import com.fm.dto.response.CustomerVisitRead;
import com.fm.model.CustomerVisit;

public class CustomerVisitMapper {

    public static CustomerVisit toEntity(ServiceWrite serviceWrite) {
        CustomerVisit customerVisit = new CustomerVisit();
        customerVisit.setMechanicId(serviceWrite.getMechanicId());
        customerVisit.setServicesPerformed(serviceWrite.getServicesPerformed());
        customerVisit.setObservations(serviceWrite.getObservations());

        return customerVisit;
    }

    public static CustomerVisitRead toDto(CustomerVisit entity) {
        CustomerVisitRead dto = new CustomerVisitRead();

        // TODO: finish this

        return dto;
    }

}
