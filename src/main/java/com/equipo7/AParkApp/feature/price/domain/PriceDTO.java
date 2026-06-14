package com.equipo7.AParkApp.feature.price.domain;

import com.equipo7.AParkApp.feature.stay.StayType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDTO {


    private UUID id;
    @Positive(message = "price Must be positive")
    @NotNull(message = "price mustn't be null")
    private double price;

    private UUID vehicleTypeId;

    private StayType stayType;
}

