package com.fm.dto.mapper;

import com.fm.dto.request.TyreWrite;
import com.fm.dto.response.TyreRead;
import com.fm.model.Tyre;

import java.util.List;
import java.util.stream.Collectors;

public class TyreMapper {

    private TyreMapper() {
    }

    public static List<Tyre> toEntity(List<TyreWrite> dtos) {
        return dtos.stream().map(TyreMapper::toEntity)
            .collect(Collectors.toList());
    }

    public static Tyre toEntity(TyreWrite dto) {
        Tyre entity = new Tyre();
        entity.setTyreBrand(dto.getTyreBrand());
        entity.setTyreSize(dto.getTyreSize());
        entity.setTyreType(dto.getTyreType());
        entity.setSeason(dto.getSeason());
        entity.setRimType(dto.getRimType());
        entity.setTreadDepth(dto.getTreadDepth());
        entity.setTyreLocation(dto.getTyreLocation());
        return entity;
    }

    public static List<TyreRead> toDto(List<Tyre> entities) {
        return entities.stream().map(TyreMapper::toDto)
            .collect(Collectors.toList());
    }

    public static TyreRead toDto(Tyre entity) {
        TyreRead dto = new TyreRead();
        dto.setId(entity.getId());
        dto.setTyreType(entity.getTyreType());
        dto.setTyreBrand(entity.getTyreBrand());

        dto.setTyreSize(entity.getTyreSize());

        dto.setWearLevel(entity.getWearLevel());
        dto.setRimType(entity.getRimType());
        return dto;
    }
}
