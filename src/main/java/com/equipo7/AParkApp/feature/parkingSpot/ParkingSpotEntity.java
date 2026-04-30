package com.equipo7.AParkApp.feature.parkingSpot;


import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor @Builder

@Table(name = "parking_spots")
public class ParkingSpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "spot_number", unique = true)
    private int number;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLotEntity parkingLot; /// Foreing Key

}
