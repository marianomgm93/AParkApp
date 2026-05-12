package com.equipo7.AParkApp.feature.offer;

import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "offers")
public class OfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLotEntity parkingLot;
    @ManyToOne
    @JoinColumn(name = "parking_spot_id", nullable = true)
    /// TODO VALIDAR DE SER NULO DEBE OBLIGATORIAMENTE CARGARSE EN TICKET
    private ParkingSpotEntity parkingSpot;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


}
