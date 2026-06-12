package com.equipo7.AParkApp.feature.driver.domain.mappers;

import com.equipo7.AParkApp.feature.driver.DriverEntity;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverRequestMapper {

    private final ModelMapper modelMapper;

    public DriverRequest toDto(DriverEntity entity) {


        return modelMapper.map(entity, DriverRequest.class);
    }

    public DriverEntity toEntity(DriverRequest dto) {

        return modelMapper.map(dto, DriverEntity.class);
    }
}
