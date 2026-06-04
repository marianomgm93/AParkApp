package com.equipo7.AParkApp.feature.employee.domain.mapper;


import com.equipo7.AParkApp.feature.employee.EmployeeEntity;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeRequest;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeResponseMapper {
    private final ModelMapper mapper;

    public EmployeeEntity toEntity(EmployeeResponse dto){
        return mapper.map(dto, EmployeeEntity.class);
    }

    public EmployeeResponse toDTO(EmployeeEntity entity){
        return mapper.map(entity, EmployeeResponse.class);
    }
}
