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
import java.util.stream.Collectors;

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
    public CustomerVisitRead saveCustomerVisit(CustomerVisitWrite customerVisitWrite) {
        Customer customer = getOrCreateCustomer(customerVisitWrite);

        CustomerVisit customerVisit = CustomerVisitMapper.toEntity(customerVisitWrite);
        customerVisit.setCustomer(customer);
        customerVisitRepository.save(customerVisit);

        StoragePoint storagePoint = StoragePointMapper.toEntity(customerVisitWrite.getStoragePointWrite());
        storagePointRepository.save(storagePoint);

        storagePoint.getMountedTyres().forEach(tyre -> {
            tyre.setStorageId(storagePoint.getId());
            tyreRepository.save(tyre);
        });
        storagePoint.getStoredTyres().forEach(tyre -> {
            tyre.setStorageId(storagePoint.getId());
            tyreRepository.save(tyre);
        });

        CustomerVisitRead result = findCustomerVisit(customerVisit.getId());
        StoragePointRead storagePointRead = findStoragePoint(storagePoint.getLicensePlate());
        result.setStoragePointRead(storagePointRead);

        log.info("Customer visit saved {}", result);
        return result;
    }

    @Override
    public List<CustomerVisitRead> findAll() {
        return customerVisitRepository.findAll().stream()
            .map(CustomerVisitMapper::toDto)
            .collect(Collectors.toList());
    }

    public CustomerVisitRead findCustomerVisit(Long id) {
        return customerVisitRepository.findById(id)
            .map(CustomerVisitMapper::toDto)
            .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public StoragePointRead findStoragePoint(String licensePlate) {
        return storagePointRepository.findStoragePoint(licensePlate)
            .map(StoragePointMapper::toDto)
            .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public Map<String, List<Tyre>> findWornTyres() {
        return storagePointRepository.findWornTyres();
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
    public void checkout(String licensePlate) {
        StoragePoint storagePoint = storagePointRepository.findStoragePoint(licensePlate)
            .orElseThrow(() -> new RuntimeException("not found"));
        storagePoint.clear();
        storagePointRepository.save(storagePoint);
    }

    private Customer getOrCreateCustomer(CustomerVisitWrite customerVisitWrite) {
        if (customerVisitWrite.getCustomerIdMaybe() == null) {
            Customer newCustomer = CustomerMapper.toEntity(customerVisitWrite.getCustomerWriteMaybe());
            customerRepository.save(newCustomer);
            return newCustomer;
        } else {
            return customerRepository.findById(customerVisitWrite.getCustomerIdMaybe())
                .orElseThrow(() -> new RuntimeException("not found"));
        }
    }

}
