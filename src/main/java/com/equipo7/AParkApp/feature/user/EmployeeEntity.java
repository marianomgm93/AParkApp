package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class EmployeeEntity extends UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal salary;

    @OneToOne
    private ParkingLotEntity parkingLot;
}
