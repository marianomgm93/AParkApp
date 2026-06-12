package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.feature.driver.domain.dto.DriverModifyRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;

import java.util.List;

public interface IDriverService {
    public DriverResponse createDriver(DriverRequest request);

    public void dropDriver(String vehiclePlate);

    public DriverResponse modifyDriver(String dni, DriverModifyRequest request);

    public DriverResponse addVehicleToDriver(String dni, String vehiclePlate);

    public DriverResponse removeVehicleToDriver(String dni, String vehiclePlate);

    public List<DriverResponse> getAllDrivers();

    public DriverResponse ListDriver(String dni);

    public List<DriverResponse> getAllActiveDrivers();

}
