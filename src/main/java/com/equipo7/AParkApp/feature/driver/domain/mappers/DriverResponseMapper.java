package com.equipo7.AParkApp.feature.driver.domain.mappers;

import com.equipo7.AParkApp.feature.driver.DriverEntity;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverResponseMapper {
    private final ModelMapper modelMapper;

    public DriverResponse toDto(DriverEntity entity) {

        return modelMapper.map(entity, DriverResponse.class);
    }

    public DriverEntity toEntity(DriverResponse dto) {

        return modelMapper.map(dto, DriverEntity.class);
    }

}
