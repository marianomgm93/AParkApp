package com.equipo7.AParkApp.feature.billing;

import com.equipo7.AParkApp.feature.adress.AdressEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "billing_data")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BillingDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @Column(nullable = false,unique = true)
    private String cuit;
    @Column(name = "business_name")
    private String businessName;
    @OneToOne
    @JoinColumn(name = "address_id")
    private AdressEntity address;
}
