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
@NoArgsConstructor
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
    private String brand;
    private String model;
    private String color;
    private String note;

}
