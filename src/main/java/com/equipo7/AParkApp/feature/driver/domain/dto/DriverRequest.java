package com.equipo7.AParkApp.feature.driver.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverRequest {

    @NotBlank(message = "You must insert a Name")
    private String name;

    @NotBlank(message = "You must enter a dni ")
    @Positive(message = "The dni must be positive")
    private String dni;

    @NotBlank(message = "You must enter a Phone Number")
    private String phone;

    @NotBlank(message = "You must enter a vehicle license plate ")
    private String patenteVehiculo;

}
