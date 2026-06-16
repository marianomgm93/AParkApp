package com.equipo7.AParkApp.feature.vehicle.domain.dto;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {
    private UUID id;
    private String plate;
    private String model;
    private String color;
    private String note;
    private String brand;
    private UUID userId;
    private VehicleTypeEnum vehicleType;

}
