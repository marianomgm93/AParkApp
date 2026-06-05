package com.equipo7.AParkApp.feature.employee.domain.mapper;

import com.equipo7.AParkApp.feature.employee.EmployeeEntity;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeRequest;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    private final ModelMapper mapper;

    public EmployeeEntity toEntity(EmployeeRequest dto) {
        return mapper.map(dto, EmployeeEntity.class);
    }

    public EmployeeResponse toDTO(EmployeeEntity entity) {
        EmployeeResponse response = mapper.map(entity, EmployeeResponse.class);
        if (entity.getUser() != null) response.setUserId(entity.getUser().getId());
        if (entity.getParkingLot() != null) response.setParkingLotId(entity.getParkingLot().getId());
        return response;
    }
}
