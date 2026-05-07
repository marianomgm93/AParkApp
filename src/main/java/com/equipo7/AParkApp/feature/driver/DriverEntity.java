package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
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
@Table(name = "drivers")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String dni;

    private String phone;

    @ManyToMany
    @JoinTable(
            name = "drivers_x_vehicles",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private List<VehicleEntity> vehicles;
}
