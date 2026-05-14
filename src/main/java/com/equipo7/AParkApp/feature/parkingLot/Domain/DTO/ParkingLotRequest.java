package com.equipo7.AParkApp.feature.parkingLot.Domain.DTO;

import com.equipo7.AParkApp.feature.address.AddressEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ParkingLotRequest {

    @NotBlank
    private String name;
    @NotBlank
    private AddressEntity address;
    @Positive
    private int capacity;
    @NotBlank
    private UserEntity owner;


}


