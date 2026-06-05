package com.equipo7.AParkApp.feature.employee.domain.dto;


import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EmployeeRequest {
    @NotNull
    private UUID userId;

    @NotNull
    private UUID parkingLotId;

    @NotNull
    private BigDecimal salary;
    @NotBlank
    private String role;
    @NotBlank
    private String notes;
}
