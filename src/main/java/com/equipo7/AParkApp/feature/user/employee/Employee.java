package com.equipo7.AParkApp.feature.user.employee;

import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employees_x_parking_lot")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @JoinColumn(name = "employee_id")
    @ManyToOne
    private UserEntity user;


    @JoinColumn(name = "parking_lot_id")
    @ManyToOne
    private ParkingLotEntity parkingLot;

    private BigDecimal salary;
    private String role;
    private String notes;
}
