package com.fm.service;

import com.fm.dto.mapper.CustomerMapper;
import com.fm.dto.mapper.CustomerVisitMapper;
import com.fm.dto.mapper.StoragePointMapper;
import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.response.CustomerVisitRead;
import com.fm.dto.response.StoragePointRead;
import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.model.StoragePoint;
import com.fm.model.Tyre;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.StoragePointRepository;
import com.fm.repository.TyreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerVisitService implements ICustomerVisitService {

    private static final Logger log = LoggerFactory.getLogger(CustomerVisitService.class);

    private final CustomerVisitRepository customerVisitRepository;
    private final CustomerRepository customerRepository;
    private final StoragePointRepository storagePointRepository;
    private final TyreRepository tyreRepository;

    @Autowired
    public CustomerVisitService(CustomerVisitRepository customerVisitRepository, CustomerRepository customerRepository, StoragePointRepository storagePointRepository, TyreRepository tyreRepository) {
        this.customerVisitRepository = customerVisitRepository;
        this.customerRepository = customerRepository;
        this.storagePointRepository = storagePointRepository;
        this.tyreRepository = tyreRepository;
    }

    @Override
    public StoragePointRead findStoragePoint(String licensePlate) {
        StoragePoint storagePoint = storagePointRepository.findStoragePoint(licensePlate)
            .orElseThrow(() -> new RuntimeException("not found"));

        return StoragePointMapper.toDto(storagePoint);
    }

    @Override
    public CustomerVisitRead saveCustomerVisit(CustomerVisitWrite customerVisitWrite) {
        Customer customer = getOrCreateCustomer(customerVisitWrite);

        CustomerVisit customerVisit = CustomerVisitMapper.toEntity(customerVisitWrite.getServiceInfo());
        customerVisit.setCustomer(customer);
        customerVisitRepository.save(customerVisit);

        StoragePoint storagePoint = StoragePointMapper.toEntity(customerVisitWrite.getStoragePointInfo());
        storagePointRepository.save(storagePoint);

        storagePoint.getMountedTyres().forEach(tyreRepository::save);
        storagePoint.getStoredTyres().forEach(tyreRepository::save);

        CustomerVisitRead result = findCustomerVisit(customerVisit.getId());
        log.info("Customer visit saved {}", result);
        return result;
    }

    public CustomerVisitRead findCustomerVisit(Long id) {
        // TODO: create full dto

        return customerVisitRepository.findById(id)
            .map(CustomerVisitMapper::toDto)
            .orElseThrow(() -> new RuntimeException("not found"));
    }

    private Customer getOrCreateCustomer(CustomerVisitWrite customerVisitWrite) {
        if (customerVisitWrite.getCustomerIdMaybe() == null) {
            Customer newCustomer = CustomerMapper.toEntity(customerVisitWrite.getCustomerInfo());
            customerRepository.save(newCustomer);
            return newCustomer;
        } else {
            return customerRepository.findById(customerVisitWrite.getCustomerIdMaybe())
                .orElseThrow(() -> new RuntimeException("not found"));
        }
    }

    @Override
    public void checkout(String licensePlate) {
        StoragePoint storagePoint = storagePointRepository.findStoragePoint(licensePlate)
            .orElseThrow(() -> new RuntimeException("not found"));
        storagePoint.clear();
        storagePointRepository.save(storagePoint);
    }

    @Override
    public void swapStorage(String sourceStorageCode, String targetStorageCode) {
        StoragePoint sourceStoragePoint = storagePointRepository.findStoragePoint(sourceStorageCode)
            .orElseThrow(() -> new RuntimeException("not found"));

        StoragePoint targetStoragePoint = storagePointRepository.findStoragePoint(targetStorageCode)
            .orElseThrow(() -> new RuntimeException("not found"));

        if (targetStoragePoint.getStoredTyres().isEmpty()) {
            String tempCode = sourceStoragePoint.getCode();
            sourceStoragePoint.setCode(targetStorageCode);
            targetStoragePoint.setCode(tempCode);
        }

        storagePointRepository.save(sourceStoragePoint);
        storagePointRepository.save(targetStoragePoint);
    }

    @Override
    public Map<String, List<Tyre>> findWornTyres() {
        return storagePointRepository.findWornTyres();
    }

}
