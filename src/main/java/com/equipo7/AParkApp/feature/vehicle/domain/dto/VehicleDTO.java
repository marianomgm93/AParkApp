package com.equipo7.AParkApp.feature.vehicle.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {
    private String plate;
    private String model;
    private String color;
    private String note;
    private String brand;
    private UUID userId;
    private UUID vehicleTypeId;

}
