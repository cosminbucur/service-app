package com.fm.service;

import com.fm.dto.CustomerDetail;
import com.fm.dto.CustomerMapper;
import com.fm.dto.CustomerVisitDetails;
import com.fm.dto.StoragePointDetail;
import com.fm.dto.TyreDetail;
import com.fm.model.Customer;
import com.fm.repository.CustomerRepository;
import com.fm.repository.HotelRepository;

import java.util.List;
import java.util.Map;

public class HotelService {

    private HotelRepository hotelRepository;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public HotelService(HotelRepository hotelRepository, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.hotelRepository = hotelRepository;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public StoragePointDetail findStoragePoint(String licensePlate) {
        return hotelRepository.findStoragePoint(licensePlate);
    }

    public void storeTyres(StoragePointDetail storagePointDetail, CustomerVisitDetails customerVisitDetails, List<TyreDetail> tyreDetails) {
        CustomerDetail customerDetail = customerVisitDetails.getCustomerDetails();
        Customer customer = customerMapper.toEntity(customerDetail);

        if (!customerRepository.existsById(customerDetail.getId())) {
            customerRepository.save(customer);
        }

        storagePointDetail.setLicensePlate(customerDetail.getLicensePlate());
        storagePointDetail.setTyreList(tyreDetails);

        hotelRepository.save(storagePointDetail);
    }

    public void unstoreTyres(StoragePointDetail storagePointDetail, CustomerVisitDetails customerVisitDetails, List<TyreDetail> tyreDetails) {
        // get storage point from db (by license plate)
        hotelRepository.getStoragePointByLicensePlate(customerVisitDetails.getCustomerDetails().getLicensePlate());
        // remove tyres
        storagePointDetail.removeTyres(tyreDetails);
        // save
        hotelRepository.save(storagePointDetail);

//        if (!storagePoint.getTyres().isEmpty()) {
//            storagePoint.removeTyres(tyres);
//            hotelRepository.save(storagePoint);
//        } else throw new RuntimeException("no tyres stored for license plate" + storagePoint.licensePlate);
    }

    public void swapStorage(StoragePointDetail oldStorage, StoragePointDetail newStorage) {
        newStorage.setTyreList(oldStorage.getTyreList());
        oldStorage.removeTyres(oldStorage.getTyreList());
    }

    public Map<String, List<TyreDetail>> getWornTyres() {
        return hotelRepository.findWornTyres();
    }

    public void notifyCustomersOnSeasonChange(List<CustomerVisitDetails> customerVisitDetails) {

    }
}
