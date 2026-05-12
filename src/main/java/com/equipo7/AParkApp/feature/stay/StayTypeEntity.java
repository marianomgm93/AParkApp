package com.equipo7.AParkApp.feature.stay;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "stay_types")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StayTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

}
