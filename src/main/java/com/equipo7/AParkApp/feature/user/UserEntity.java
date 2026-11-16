package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.auth.credentials.CredentialsEntity;
import jakarta.persistence.*;

import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private boolean active;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private CredentialsEntity credentials;
}
