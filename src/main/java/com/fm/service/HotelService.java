package com.fm.service;

import com.fm.dto.CustomerInfo;
import com.fm.dto.CustomerMapper;
import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.ServiceInfo;
import com.fm.dto.StoragePointInfo;
import com.fm.dto.TyreInfo;
import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.HotelRepository;

import java.util.List;
import java.util.Map;

public class HotelService {

    private HotelRepository hotelRepository;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerVisitRepository customerVisitRepository;

    public HotelService(HotelRepository hotelRepository, CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerVisitRepository customerVisitRepository) {
        this.hotelRepository = hotelRepository;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerVisitRepository = customerVisitRepository;
    }

    public StoragePointInfo findStoragePoint(String licensePlate) {
        return hotelRepository.findStoragePoint(licensePlate);
    }

    // TODO: should move this logic to mounting service

    public void storeTyres(StoragePointInfo storagePointInfo, CustomerVisitInfo customerVisitInfo, List<TyreInfo> tyres) {
        CustomerInfo customerInfo = customerVisitInfo.getCustomerInfo();
        Customer customer = customerMapper.toEntity(customerInfo);

        if (!customerRepository.existsById(customerInfo.getId())) {
            customerRepository.save(customer);
        }

        ServiceInfo serviceInfo = customerVisitInfo.getServiceInfo();
        CustomerVisit customerVisit = toCustomerVisit(serviceInfo);

        customerVisitRepository.save(customerVisit);

        storagePointInfo.setLicensePlate(customerInfo.getLicensePlate());
        storagePointInfo.setMountedTyres(tyres);

        hotelRepository.save(storagePointInfo);
    }

    public void unstoreTyres(StoragePointInfo storagePointInfo, CustomerVisitInfo customerVisitInfo, List<TyreInfo> tyreInfos) {
        // get storage point from db (by license plate)
        hotelRepository.findStoragePointByLicensePlate(customerVisitInfo.getCustomerInfo().getLicensePlate());
        // remove tyres
        storagePointInfo.removeTyres(tyreInfos);
        // save
        hotelRepository.save(storagePointInfo);

//        if (!storagePoint.getTyres().isEmpty()) {
//            storagePoint.removeTyres(tyres);
//            hotelRepository.save(storagePoint);
//        } else throw new RuntimeException("no tyres stored for license plate" + storagePoint.licensePlate);
    }

    public void swapStorage(StoragePointInfo oldStorage, StoragePointInfo newStorage) {
        newStorage.setMountedTyres(oldStorage.getMountedTyres());
        oldStorage.removeTyres(oldStorage.getMountedTyres());
    }

    public Map<String, List<TyreInfo>> getWornTyres() {
        return hotelRepository.findWornTyres();
    }

    public void notifyCustomersOnSeasonChange(List<CustomerVisitInfo> customerVisits) {

    }

    private CustomerVisit toCustomerVisit(ServiceInfo serviceInfo) {
        CustomerVisit customerVisit = new CustomerVisit();
        customerVisit.setVisitDate(serviceInfo.getVisitDate());
        customerVisit.setMechanicId(serviceInfo.getMechanicId());
        customerVisit.setServicesPerformed(serviceInfo.getServicesPerformed());
        customerVisit.setObservations(serviceInfo.getObservations());
        return customerVisit;
    }
}
