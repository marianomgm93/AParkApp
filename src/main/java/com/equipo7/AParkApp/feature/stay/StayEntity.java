package com.equipo7.AParkApp.feature.stay;

import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.ticket.TicketEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stays")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "stay_type_id", nullable = false)
    private StayTypeEntity type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "parking_spot_id", nullable = false)
    private ParkingSpotEntity parkingSpot;

    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private BigDecimal totalCost;
}
