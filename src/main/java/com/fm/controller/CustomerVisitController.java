package com.fm.controller;

import com.fm.dto.CustomerVisitInfo;
import com.fm.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/visits")
@RestController
public class CustomerVisitController {

    private HotelService hotelService;

    @Autowired
    public CustomerVisitController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // TODO: save customer visit
    @PostMapping
    public ResponseEntity<CustomerVisitInfo> saveCustomerVisit(@RequestBody CustomerVisitInfo customerVisitInfo) {
        // TODO: add validation later
        return ResponseEntity.ok(hotelService.saveCustomerVisit(customerVisitInfo));
    }

    // TODO: find storage point
    void findStoragePoint(String licensePlate) {

    }
}
