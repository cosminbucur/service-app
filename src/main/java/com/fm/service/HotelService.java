package com.fm.service;

import com.fm.dto.CustomerVisit;
import com.fm.dto.StoragePoint;
import com.fm.dto.Tyre;
import com.fm.model.Customer;
import com.fm.repository.CustomerRepository;
import com.fm.repository.HotelRepository;

import java.util.List;
import java.util.Map;

public class HotelService {

    private HotelRepository hotelRepository;
    private CustomerRepository customerRepository;

    public HotelService(HotelRepository hotelRepository, CustomerRepository customerRepository) {
        this.hotelRepository = hotelRepository;
        this.customerRepository = customerRepository;
    }

    public StoragePoint findStoragePoint(String licensePlate) {
        return hotelRepository.findStoragePoint(licensePlate);
    }

    public void storeTyres(Customer customer, StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> tyres) {
        // TODO: save customer
        CustomerRepository customerRepository = null;

        storagePoint.setLicensePlate(customerVisit.getLicensePlate());
        storagePoint.setTyres(tyres);

        customerRepository.save(customer);
        hotelRepository.save(storagePoint);

    }

    public void unstoreTyres(StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> tyres) {
        // get storage point from db (by license plate)
        hotelRepository.getStoragePointByLicensePlate(customerVisit.getLicensePlate());
        // remove tyres
        storagePoint.removeTyres(tyres);
        // save
        hotelRepository.save(storagePoint);

//        if (!storagePoint.getTyres().isEmpty()) {
//            storagePoint.removeTyres(tyres);
//            hotelRepository.save(storagePoint);
//        } else throw new RuntimeException("no tyres stored for license plate" + storagePoint.licensePlate);
    }

    public void swapStorage(StoragePoint oldStorage, StoragePoint newStorage) {
        newStorage.setTyres(oldStorage.getTyres());
        oldStorage.removeTyres(oldStorage.getTyres());
    }

    public Map<String, List<Tyre>> getWornTyres() {
        return hotelRepository.findWornTyres();
    }

    public void notifyCustomersOnSeasonChange(List<CustomerVisit> customerVisits) {

    }
}
