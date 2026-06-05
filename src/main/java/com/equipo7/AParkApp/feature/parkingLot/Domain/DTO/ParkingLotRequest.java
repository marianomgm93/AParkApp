package com.equipo7.AParkApp.feature.parkingLot.Domain.DTO;

import com.equipo7.AParkApp.feature.address.AddressEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ParkingLotRequest {

    @NotBlank(message ="You must enter a correct name")
    private String name;
    @NotNull(message ="You must enter a correct address id")
    private UUID addressId;
    @Positive(message ="The capacity must be above zero")
    private int capacity;
    @NotNull(message ="You must enter a correct owner id")
    private UUID ownerId;


}


