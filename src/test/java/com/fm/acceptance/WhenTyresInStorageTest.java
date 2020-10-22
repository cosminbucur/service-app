package com.fm.acceptance;

import com.fm.dto.CustomerMapper;
import com.fm.dto.StoragePointDetail;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerRepositoryInMemory;
import com.fm.repository.HotelRepository;
import com.fm.repository.HotelRepositoryInMemory;
import com.fm.service.HotelService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WhenTyresInStorageTest {

    private final HotelRepository hotelRepository = new HotelRepositoryInMemory();
    private final CustomerRepository customerRepository = new CustomerRepositoryInMemory();
    private final CustomerMapper customerMapper = new CustomerMapper();
    private final HotelService hotelService = new HotelService(hotelRepository, customerRepository, customerMapper);

    @Test
    void shouldSwapStorage() {
        StoragePointDetail oldStorage = new StoragePointDetail();
        StoragePointDetail newStorage = new StoragePointDetail();

        oldStorage.setTyreList(TestUtils.createFourTyres());

        hotelService.swapStorage(oldStorage, newStorage);

        assertThat(oldStorage.getTyreList()).isEmpty();
        assertThat(newStorage.getTyreList().size()).isEqualTo(4);
    }

}
