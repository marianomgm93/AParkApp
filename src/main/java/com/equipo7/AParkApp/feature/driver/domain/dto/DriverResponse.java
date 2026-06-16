package com.equipo7.AParkApp.feature.driver.domain.dto;

import jakarta.persistence.Column;

import java.util.UUID;

public class DriverResponse {

    private UUID id;

    private String name;

    private String dni;

    private String phone;
}
