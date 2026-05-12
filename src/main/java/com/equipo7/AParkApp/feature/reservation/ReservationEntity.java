package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate startTime;

    private LocalDate endTime;

    private String status;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLotEntity parkingLot;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

}
