package com.fm.controller;

import com.fm.dto.CustomerVisitInfo;
import com.fm.model.CustomerVisit;
import com.fm.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CustomerVisitController {

    private static final Logger log = LoggerFactory.getLogger(CustomerVisitController.class);
    private HotelService hotelService;

    // TODO: save customer visit
    @PostMapping
    public CustomerVisit saveCustomerVisit(@RequestBody CustomerVisitInfo customerVisitInfo) {
        log.info("customer visit created: {}", customerVisitInfo);

        return hotelService.saveCustomerVisit(customerVisitInfo);
    }

    // TODO: find storage point
    void findStoragePoint(String licensePlate) {

    }
}
