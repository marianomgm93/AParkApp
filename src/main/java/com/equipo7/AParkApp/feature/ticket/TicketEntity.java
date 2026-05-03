package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.stay.StayEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "stay_id", nullable = false, unique = true)
    private StayEntity stay;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @OneToOne
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpotEntity parkingSpot;
}
