package com.equipo7.AParkApp.feature.vehicle.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {
    @NotBlank(message = "You must insert a plate")
    private String plate;

    private String model;
    private String color;
    private String note;

    private String brand;
}
