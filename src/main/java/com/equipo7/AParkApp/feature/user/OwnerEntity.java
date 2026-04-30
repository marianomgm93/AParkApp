package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder

@Table(name = "owners")
public class OwnerEntity extends UserEntity{
    public OwnerEntity(Long id, String name, String email, List<ParkingLotEntity> parkingLotEntityList) {
        super(id, name, email);
    }



}
