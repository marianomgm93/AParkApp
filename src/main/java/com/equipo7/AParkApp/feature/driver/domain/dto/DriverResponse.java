package com.equipo7.AParkApp.feature.driver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor

public class DriverResponse {

    private UUID id;

    private String name;

    private String dni;

    private String phone;
}
