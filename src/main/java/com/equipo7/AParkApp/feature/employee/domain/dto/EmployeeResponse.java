package com.equipo7.AParkApp.feature.employee.domain.dto;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;

import java.math.BigDecimal;

public class EmployeeResponse {
    private UserEntity user;
    private ParkingLotEntity parkingLot;
    private BigDecimal salary;
    private String role;
    private String notes;
}
