package com.fm.service;

import com.fm.dto.CustomerVisitDetails;
import com.fm.dto.TyreDetail;
import com.fm.dto.Vehicle;
import com.fm.model.Mechanic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MountingService {

    private List<TyreDetail> newTyreDetails = new ArrayList<>();

    public void replaceTyres(CustomerVisitDetails customerVisitDetails, long mechanicId, String licensePlate, Vehicle vehicleWithOldTyres, Vehicle vehicleWithNewTyres) {

        Mechanic.mechanic().setId(mechanicId);

        customerVisitDetails.setVisitDate(LocalDate.now());
        customerVisitDetails.getCustomerDetails().setLicensePlate(licensePlate);

        vehicleWithOldTyres.licensePlate = customerVisitDetails.getCustomerDetails().getLicensePlate();
        vehicleWithNewTyres.licensePlate = vehicleWithOldTyres.licensePlate;
        vehicleWithNewTyres.mountTyres(newTyreDetails);
    }
}
