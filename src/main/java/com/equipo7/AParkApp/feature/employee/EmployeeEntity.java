package com.equipo7.AParkApp.feature.employee;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
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
@Table(name = "employees_x_parking_lot",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "parking_lot_id"})
        }
)
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLotEntity parkingLot;

    private BigDecimal salary;
    private String role;
    private String notes;
}
