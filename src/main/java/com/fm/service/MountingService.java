package com.fm.service;

import com.fm.dto.CustomerVisit;
import com.fm.dto.Tyre;
import com.fm.dto.Vehicle;
import com.fm.model.Mechanic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MountingService {

    private List<Tyre> newTyres = new ArrayList<>();

    public void replaceTyres(CustomerVisit customerVisit, long mechanicId, String licensePlate, Vehicle vehicleWithOldTyres, Vehicle vehicleWithNewTyres) {

        Mechanic.mechanic().setId(mechanicId);

        customerVisit.setVisitDate(LocalDate.now());
        customerVisit.setLicensePlate(licensePlate);

        vehicleWithOldTyres.licensePlate = customerVisit.getLicensePlate();
        vehicleWithNewTyres.licensePlate = vehicleWithOldTyres.licensePlate;
        vehicleWithNewTyres.mountTyres(newTyres);
    }
}
