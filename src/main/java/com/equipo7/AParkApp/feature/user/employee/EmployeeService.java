package com.equipo7.AParkApp.feature.user.employee;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto findById(UUID id) {

        EmployeeEntity employee =
                employeeRepository.findById(id).orElse(null);

        if(employee == null) {
            return null;
        }

        return mapToDto(employee);
    }

    private EmployeeDto mapToDto(EmployeeEntity employee) {

        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                null
        );
    }



    @Override
    public EmployeeEntity save(EmployeeEntity employee) {

        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(EmployeeEntity employee) {
        employeeRepository.delete(employee);



    }



    @Override
    public void update(UUID id, EmployeeEntity employee) {


    }

    @Override
    public List<EmployeeEntity> findAll() {
        return List.of();
    }


}
