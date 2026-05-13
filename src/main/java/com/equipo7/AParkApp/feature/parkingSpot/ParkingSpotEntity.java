package com.equipo7.AParkApp.feature.parkingSpot;


import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_spots")
public class ParkingSpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "spot_number", unique = true)
    private int number;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLotEntity parkingLot; /// Foreing Key

}
