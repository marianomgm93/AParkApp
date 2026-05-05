package com.equipo7.AParkApp.feature.user;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public abstract class UserEntity {
    private String name;

    @Column(unique = true)
    private String email;


    public UserEntity() {
    }
}
