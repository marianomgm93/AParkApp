package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.driver.DriverEntity;
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
@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "vehicle_types_id")
    private VehicleTypeEntity vehicleType;

    @ManyToMany
    @JoinTable(
        name = "diver_vehicle",
        joinColumns = @JoinColumn(name = "vehicle_id"),
        inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<DriverEntity> drivers;

}
