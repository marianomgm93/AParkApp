package com.equipo7.AParkApp.feature.billing;

import com.equipo7.AParkApp.feature.stay.StayEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BillingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "stay_id")
    private StayEntity stay;
    private BigDecimal amount;
    @CreationTimestamp
    private LocalDateTime timeStamp;
    private boolean status;
}
