package com.equipo7.AParkApp.feature.address.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    @NotBlank(message = "You must enter a street")
    private String street;
    @PositiveOrZero(message = "Your number must be positive or zero if your domicile doesn't have a number")
    private Integer number;
    @NotBlank(message = "You must enter a valid zipcode")
    private String zipCode;
    private String notes;
}
