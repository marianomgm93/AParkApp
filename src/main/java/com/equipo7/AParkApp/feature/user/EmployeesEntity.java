package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder

@Table(name = "employees")
public class EmployeesEntity extends UserEntity{
    public EmployeesEntity(Long id, String name, String email) {
        super(id, name, email);
    }

    @OneToMany(mappedBy = "parkinLot")
    private List<ParkingLotEntity> parkingLotEntityList;
}
