package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.common.model.exceptions.EntityAlreadyExistsEx;
import com.equipo7.AParkApp.feature.driver.domain.IDriverService;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;
import com.equipo7.AParkApp.feature.driver.domain.mappers.DriverRequestMapper;
import com.equipo7.AParkApp.feature.driver.domain.mappers.DriverResponseMapper;
import com.equipo7.AParkApp.feature.vehicle.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService implements IDriverService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    private final DriverRequestMapper requestMapper;
    private final DriverResponseMapper responseMapper;


    @Override
    public DriverResponse createDriver(DriverRequest request) {

        if (driverRepository.existsByDni(request.getDni())) {
            throw new EntityAlreadyExistsEx("Driver Already exists");
        }


        return null;
    }

    @Override
    public DriverResponse dropDriver(String vehiclePlate) {
        return null;
    }

    @Override
    public DriverResponse modifyDriver(String dni) {
        return null;
    }

    @Override
    public DriverResponse addVehicleToDriver(String dni, String vehiclePlate) {
        return null;
    }

    @Override
    public DriverResponse removeVehicleToDriver(String dni, String vehiclePlate) {
        return null;
    }

    @Override
    public List<DriverResponse> getAllDrivers() {
        return null;
    }

    @Override
    public DriverResponse ListDriver(String name, String dni) {
        return null;
    }

    @Override
    public DriverResponse getAllActiveDrivers() {
        return null;
    }
}
