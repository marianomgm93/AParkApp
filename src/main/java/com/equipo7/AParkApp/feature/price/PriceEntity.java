package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.stay.StayTypeEntity;
import com.equipo7.AParkApp.feature.vehicle.Type.VehicleTypeEntity;
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
    private double price;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleTypeEntity vehicleTypes;

    @ManyToOne
    @JoinColumn(name = "stay_type_id")
    private StayTypeEntity stayType;

}
