package com.fm.service;

import com.fm.dto.CustomerInfo;
import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.ObjectMapper;
import com.fm.dto.ServiceInfo;
import com.fm.dto.StoragePointInfo;
import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.model.StoragePoint;
import com.fm.model.Tyre;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.StoragePointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HotelService implements IHotelService {

    private static final Logger log = LoggerFactory.getLogger(HotelService.class);

    private CustomerVisitRepository customerVisitRepository;
    private CustomerRepository customerRepository;
    private StoragePointRepository storagePointRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public HotelService(CustomerVisitRepository customerVisitRepository, CustomerRepository customerRepository, StoragePointRepository storagePointRepository, ObjectMapper objectMapper) {
        this.storagePointRepository = storagePointRepository;
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
        this.customerVisitRepository = customerVisitRepository;
    }

    @Override
    public StoragePointInfo findStoragePoint(String licensePlate) {
        StoragePoint storagePoint = storagePointRepository.findStoragePoint(licensePlate)
            .orElseThrow(() -> new RuntimeException("not found"));

        return objectMapper.toDto(storagePoint);
    }

    @Override
    public CustomerVisitInfo saveCustomerVisit(CustomerVisitInfo customerVisitInfo) {
        CustomerInfo customerInfo = customerVisitInfo.getCustomerInfo();
        Customer customer = objectMapper.toEntity(customerInfo);

        if (!customerRepository.existsById(customerInfo.getId())) {
            customerRepository.save(customer);
        }

        ServiceInfo serviceInfo = customerVisitInfo.getServiceInfo();
        CustomerVisit customerVisit = objectMapper.toEntity(serviceInfo);
        customerVisit.setCustomer(customer);

        customerVisitRepository.save(customerVisit);

        StoragePointInfo storagePointInfo = customerVisitInfo.getStoragePointInfo();
        storagePointInfo.setLicensePlate(customerInfo.getLicensePlate());

        StoragePoint storagePoint = objectMapper.toEntity(storagePointInfo);

        storagePointRepository.save(storagePoint);

        // TODO: use mapper
        return new CustomerVisitInfo();
    }

    @Override
    public void checkout(String licensePlate) {
        StoragePoint storagePoint = storagePointRepository.findStoragePoint(licensePlate)
            .orElseThrow(() -> new RuntimeException("not found"));
        storagePoint.clear();
        storagePointRepository.save(storagePoint);
    }

    // TODO: fix this
    @Override
    public void swapStorage(StoragePointInfo oldStorageInfo, StoragePointInfo newStorageInfo) {
        StoragePoint oldStoragePoint = storagePointRepository.findStoragePoint(oldStorageInfo.getId())
            .orElseThrow(() -> new RuntimeException("not found"));

        oldStoragePoint.clear();

        StoragePoint newStoragePoint = storagePointRepository.findStoragePoint(oldStorageInfo.getId())
            .orElseThrow(() -> new RuntimeException("not found"));

        newStoragePoint.setNumberOfRimCaps(newStorageInfo.getNumberOfRimCaps());
        newStoragePoint.setLicensePlate(newStorageInfo.getLicensePlate());
//        newStoragePoint.setMountedTyres(newStorageInfo.getMountedTyres());
//        newStoragePoint.setStoredTyres(newStorageInfo.getStoredTyres());

        storagePointRepository.save(oldStoragePoint);
        storagePointRepository.save(newStoragePoint);
    }

    @Override
    public Map<String, List<Tyre>> findWornTyres() {
        return storagePointRepository.findWornTyres();
    }

    @Override
    public void notifyCustomersOnSeasonChange(List<CustomerVisitInfo> customerVisits) {

    }

}
