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
    @NotBlank
    private String plate;
    @NotBlank
    private String model;
    @NotBlank
    private String color;
    @NotBlank
    private String note;
    @NotBlank
    private String brand;
}
