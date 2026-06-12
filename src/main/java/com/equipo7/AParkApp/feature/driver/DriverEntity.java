package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Entity
@Table(name = "drivers")
@NoArgsConstructor
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String dni;

    @Column(unique = true,nullable = false)
    private String phone;

    @Column(nullable = false)
    private Boolean active;

    @ManyToMany
    @JoinTable(
            name = "drivers_x_vehicles",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )

    private List<VehicleEntity> vehicles=new ArrayList<>();
}
