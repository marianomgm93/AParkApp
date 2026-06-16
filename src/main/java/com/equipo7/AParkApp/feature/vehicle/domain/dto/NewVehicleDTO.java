package com.equipo7.AParkApp.feature.vehicle.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewVehicleDTO {
    @NotBlank(message = "You must insert a vehicle plate")
    private String plate;
    @NotBlank(message = "You must insert an user ID plate")
    private UUID userId;
    @NotBlank(message = "You must insert a vehicle model")
    private String model;
    @NotBlank(message = "You must insert the color of the vehicle")
    private String color;
    private String note;
    @NotBlank(message = "You must insert the brand of the vehicle")
    private String brand;
    @NotBlank(message = "You must insert A vehicle Type id")
    private UUID vehicleTypeId;
}
