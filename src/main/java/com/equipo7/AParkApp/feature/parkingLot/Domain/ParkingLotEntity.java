package com.equipo7.AParkApp.feature.parkingLot.Domain;

import com.equipo7.AParkApp.feature.address.AddressEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@NoArgsConstructor
public class ParkingLotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(nullable = false)
    private boolean active;


    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "total_capacity")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

}
