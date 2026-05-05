package com.equipo7.AParkApp.feature.user;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class UserEntity {
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
}
