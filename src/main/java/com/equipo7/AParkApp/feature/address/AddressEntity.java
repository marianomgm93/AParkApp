package com.equipo7.AParkApp.feature.adress;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(name = "adresses")
public class AdressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer number;
    @Column(name = "zip_code")
    private String zipCode;
    private String notes;
}
