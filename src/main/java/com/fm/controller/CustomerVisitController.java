package com.fm.controller;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.response.CustomerVisitRead;
import com.fm.service.CustomerVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/visits")
@RestController
public class CustomerVisitController {

    private final CustomerVisitService customerVisitService;

    @Autowired
    public CustomerVisitController(CustomerVisitService customerVisitService) {
        this.customerVisitService = customerVisitService;
    }

    @PostMapping
    public ResponseEntity<CustomerVisitRead> saveCustomerVisit(@RequestBody CustomerVisitWrite customerVisitWrite) {
        // TODO: add validation later
        return ResponseEntity.ok(customerVisitService.saveCustomerVisit(customerVisitWrite));
    }

    void findStoragePoint(String licensePlate) {
        // TODO: find storage point
    }
}
