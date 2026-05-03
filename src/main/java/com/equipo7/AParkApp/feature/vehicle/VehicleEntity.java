package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.driver.DriverEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder

@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "vehicle_tipes_id")
    private VehicleTypeEntity vehicleType;

    @ManyToOne
    @JoinColumn(name = "drivers_id")
    private DriverEntity driver;

}
