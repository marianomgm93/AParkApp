package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.offer.OfferEntity;
import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLotEntity parkingLot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_spot_id", nullable = true)
    private ParkingSpotEntity parkingSpot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private OfferEntity offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
