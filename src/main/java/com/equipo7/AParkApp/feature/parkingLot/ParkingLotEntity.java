package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.address.AddressEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
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
@Table(name = "parking_lots")
public class ParkingLotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;


    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "total_capacity")
    private int capacity;

    ///TODO VALIDAR PARA SOLO USUARIOS CON ROL DE OWNER
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
}
