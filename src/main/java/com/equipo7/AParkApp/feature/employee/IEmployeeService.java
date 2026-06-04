package com.equipo7.AParkApp.feature.employee;

import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeRequest;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {
    EmployeeResponse create(EmployeeRequest request);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(UUID id);
    EmployeeResponse update(UUID id, EmployeeRequest request);
}
