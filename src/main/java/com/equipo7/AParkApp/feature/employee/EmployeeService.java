package com.equipo7.AParkApp.feature.employee;

import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeRequest;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeResponse;
import com.equipo7.AParkApp.feature.employee.domain.mapper.EmployeeRequestMapper;
import com.equipo7.AParkApp.feature.employee.domain.mapper.EmployeeResponseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository repository;
    private final EmployeeRequestMapper requestMapper;
    private final EmployeeResponseMapper responseMapper;


    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        EmployeeEntity employeeEntity = requestMapper.toEntity(request);

        EmployeeEntity newEmployeeEntity = repository.save(employeeEntity);

        return responseMapper.toDTO(newEmployeeEntity);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeEntity> allEmployees = repository.findAll();

        return allEmployees.stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(UUID id) {
        EmployeeEntity found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee Not Found"));

        return responseMapper.toDTO(found);
    }

    @Override
    public EmployeeResponse update(UUID id, EmployeeRequest request) {
        EmployeeEntity entity =
                repository.findById(id).
                        orElseThrow(() -> new EntityNotFoundException("Employee Not Found"));

        entity.setUser(request.getUser());
        entity.setNotes(request.getNotes());
        entity.setSalary(request.getSalary());
        entity.setParkingLot(request.getParkingLot());
        entity.setRole(request.getRole());

        EmployeeEntity upgradeEmployeeEntity = repository.save(entity);

        return responseMapper.toDTO(upgradeEmployeeEntity);
    }
}
