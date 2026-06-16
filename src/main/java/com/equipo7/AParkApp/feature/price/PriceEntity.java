package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEnum;
import com.equipo7.AParkApp.feature.stay.StayType;
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
@Table(name = "prices")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "price")
    private Double price;

    @Column(nullable = false, name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleTypeEnum vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StayType stayType;

}
