package com.equipo7.AParkApp.feature.price.domain;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEnum;
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
    @NotNull(message = "vehicle type mustn't be null")
    private VehicleTypeEnum vehicleType;

    @NotNull(message = "stay type mustn't be null")
    private StayType stayType;
}

