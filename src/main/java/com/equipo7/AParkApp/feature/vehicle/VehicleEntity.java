package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEnum;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String plate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "vehicle_type")
    private VehicleTypeEnum vehicleType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String brand;

    private String model;

    private String color;

    private String note;

}
