package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.user.owner.OwnerEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_lots")
public class ParkingLotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String address;

    @Column(name = "total_capacity")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;
}
