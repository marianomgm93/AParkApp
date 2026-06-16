package com.equipo7.AParkApp.feature.address.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class AddressResponse {

    private UUID id;
    private String street;
    private Integer number;
    private String zipCode;
    private String notes;
}
