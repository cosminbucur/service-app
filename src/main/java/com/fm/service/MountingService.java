package com.fm.service;

import com.fm.dto.CustomerVisitInfo;

public class MountingService {

    public void replaceTyres(CustomerVisitInfo customerVisitInfo, long mechanicId, String licensePlate) {


        customerVisitInfo.getCustomerInfo().setLicensePlate(licensePlate);
    }
}
