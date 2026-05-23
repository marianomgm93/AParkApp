package com.equipo7.AParkApp.feature.vehicle.domain.dto;

import java.util.UUID;

public record NewVehicleDTO(UUID vehicleId,
                            String plate,
                            String model,
                            String color,
                            String note,
                            String brand) {

}
