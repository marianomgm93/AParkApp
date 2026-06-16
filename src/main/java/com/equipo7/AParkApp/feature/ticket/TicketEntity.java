package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.reservation.ReservationEntity;
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

    @Builder.Default
    private BigDecimal amount = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal paid = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime timeStamp;

    private TicketStatus status;
    private String note;
}
