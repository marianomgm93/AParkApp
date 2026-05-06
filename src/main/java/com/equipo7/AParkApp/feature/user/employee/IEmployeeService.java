package com.equipo7.AParkApp.feature.user.employee;

import com.equipo7.AParkApp.feature.user.owner.OwnerEntity;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {

    EmployeeEntity  save(EmployeeEntity owner);
    void delete(EmployeeEntity owner);
    void update(UUID id,EmployeeEntity owner);
    List<EmployeeEntity> findAll();
    EmployeeDto findById(UUID id);





}
