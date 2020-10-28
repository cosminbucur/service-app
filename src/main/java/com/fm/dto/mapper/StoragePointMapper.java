package com.fm.dto.mapper;

import com.fm.dto.request.StoragePointWrite;
import com.fm.dto.response.StoragePointRead;
import com.fm.model.StoragePoint;

public class StoragePointMapper {

    private StoragePointMapper() {
    }

    public static StoragePoint toEntity(StoragePointWrite dto) {
        StoragePoint entity = new StoragePoint();
        entity.setLicensePlate(dto.getLicensePlate());
        entity.setNumberOfRimCaps(dto.getNumberOfRimCaps());
        entity.setMountedTyres(TyreMapper.toEntity(dto.getStoredTyres()));
        entity.setStoredTyres(TyreMapper.toEntity(dto.getStoredTyres()));
        return entity;
    }

    public static StoragePointRead toDto(StoragePoint entity) {
        StoragePointRead dto = new StoragePointRead();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setNumberOfRimCaps(entity.getNumberOfRimCaps());
        dto.setLicensePlate(entity.getLicensePlate());

        dto.setMountedTyres(TyreMapper.toDto(entity.getMountedTyres()));
        dto.setStoredTyres(TyreMapper.toDto(entity.getStoredTyres()));
        return dto;
    }
}
