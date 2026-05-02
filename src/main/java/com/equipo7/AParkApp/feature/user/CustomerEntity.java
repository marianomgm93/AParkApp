package com.equipo7.AParkApp.feature.user;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

@Table(name = "customer")
public class CustomerEntity extends UserEntity{
    public CustomerEntity(Long id, String name, String email) {
        super(id, name, email);
    }

    private String phone;
}
