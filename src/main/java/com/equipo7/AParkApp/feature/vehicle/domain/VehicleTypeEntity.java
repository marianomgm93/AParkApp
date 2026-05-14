package com.equipo7.AParkApp.feature.vehicle.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicle_types")
public class VehicleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "vehicleType")
    private List<VehicleEntity> vehicles;
}
