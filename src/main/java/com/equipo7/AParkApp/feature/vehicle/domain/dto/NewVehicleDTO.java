package com.equipo7.AParkApp.feature.vehicle.domain.dto;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "You must insert a user ID")
    private UUID userId;

    @NotBlank(message = "You must insert a vehicle model")
    private String model;

    @NotBlank(message = "You must insert the color of the vehicle")
    private String color;

    private String note;

    @NotBlank(message = "You must insert the brand of the vehicle")
    private String brand;
    @NotNull(message = "You must insert the type of the vehicle")
    private VehicleTypeEnum vehicleType;

}
