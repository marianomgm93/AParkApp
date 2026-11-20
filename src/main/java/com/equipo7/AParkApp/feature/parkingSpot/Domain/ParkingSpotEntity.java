package com.equipo7.AParkApp.feature.parkingSpot.Domain;


import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
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
@Table(
        name = "parking_spots",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"parking_lot_id", "spot_number"})
        }
)
@NoArgsConstructor
public class ParkingSpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;


    private boolean active;

    @Column(name = "status")
    private Status status;

    @Column(name = "spot_number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLotEntity parkingLot; /// Foreing Key

}
