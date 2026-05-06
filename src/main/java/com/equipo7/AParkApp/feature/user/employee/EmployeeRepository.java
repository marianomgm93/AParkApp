package com.equipo7.AParkApp.feature.user.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity, UUID> {




}

