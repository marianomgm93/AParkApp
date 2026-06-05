package com.equipo7.AParkApp.feature.employee.domain.dto;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class EmployeeResponse {
    private UUID id;
    private UUID userId;
    private UUID parkingLotId;
    private BigDecimal salary;
    private String role;
    private String notes;
}
