package com.equipo7.AParkApp.feature.driver.domain;

import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;

import java.util.List;
import java.util.UUID;

public interface IDriverService {
    public DriverResponse createDriver(DriverRequest request);
    public DriverResponse dropDriver(String vehiclePlate);

    public DriverResponse modifyDriver(String dni);

    public DriverResponse addVehicleToDriver(String dni,String vehiclePlate);

    public DriverResponse removeVehicleToDriver(String dni,String vehiclePlate);

    public List<DriverResponse> getAllDrivers();

    public DriverResponse ListDriver(String name,String dni);

    public DriverResponse getAllActiveDrivers();

}
