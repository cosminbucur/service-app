package com.bucur.service;

import com.bucur.dto.CustomerVisit;
import com.bucur.dto.Tyre;
import com.bucur.dto.Vehicle;
import com.bucur.model.Mechanic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MountingService {

    private List<Tyre> newTyres = new ArrayList<>();

    public void replaceTyres(CustomerVisit customerVisit, long mechanicId, String licensePlate, Vehicle vehicleWithOldTyres, Vehicle vehicleWithNewTyres) {

        Mechanic.mechanic().setId(mechanicId);

        customerVisit.setVisitDate(LocalDate.now());
        customerVisit.setLicensePlates(licensePlate);

        vehicleWithOldTyres.licensePlate = customerVisit.getLicensePlates();
        vehicleWithNewTyres.licensePlate = vehicleWithOldTyres.licensePlate;
        vehicleWithNewTyres.mountTyres(newTyres);
    }
}
