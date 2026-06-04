package com.equipo7.AParkApp.feature.vehicle.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewVehicleDTO {
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
