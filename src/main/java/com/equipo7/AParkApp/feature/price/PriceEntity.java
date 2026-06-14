package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.stay.StayType;
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
@Table(name = "prices")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleTypeEntity vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StayType stayType;

}
