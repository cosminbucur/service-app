package com.fm.service;

import com.fm.dto.CustomerVisitDetails;
import com.fm.dto.TyreDetail;
import com.fm.model.Mechanic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MountingService {

    private List<TyreDetail> newTyreDetails = new ArrayList<>();

    public void replaceTyres(CustomerVisitDetails customerVisitDetails, long mechanicId, String licensePlate) {

        Mechanic.mechanic().setId(mechanicId);

        customerVisitDetails.setVisitDate(LocalDate.now());
        customerVisitDetails.getCustomerDetails().setLicensePlate(licensePlate);
    }
}
