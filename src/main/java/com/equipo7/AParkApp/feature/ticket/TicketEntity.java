package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.reservation.ReservationEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false, unique = true)
    private ReservationEntity reservation;

    @OneToOne
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpotEntity parkingSpot;

    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime timeStamp;

    private boolean status;
    private String note;
}
