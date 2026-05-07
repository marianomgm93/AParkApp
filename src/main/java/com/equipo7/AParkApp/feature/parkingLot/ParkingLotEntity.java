package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.adress.AdressEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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


    @OneToOne
    @JoinColumn(name = "address_id")
    private AdressEntity address;

    @Column(name = "total_capacity")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
}
