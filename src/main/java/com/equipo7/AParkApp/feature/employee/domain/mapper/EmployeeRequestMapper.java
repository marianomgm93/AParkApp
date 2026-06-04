package com.equipo7.AParkApp.feature.employee.domain.mapper;

import com.equipo7.AParkApp.feature.employee.EmployeeEntity;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeRequestMapper {
    private final ModelMapper mapper;

    public EmployeeEntity toEntity(EmployeeRequest dto){
        return mapper.map(dto, EmployeeEntity.class);
    }

    public EmployeeRequest toDTO(EmployeeEntity entity){
        return mapper.map(entity, EmployeeRequest.class);
    }
}
