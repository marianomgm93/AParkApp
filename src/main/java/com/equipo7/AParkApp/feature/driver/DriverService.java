package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.common.model.exceptions.BadRequestEx;
import com.equipo7.AParkApp.common.model.exceptions.EntityAlreadyExistsEx;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverModifyRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;
import com.equipo7.AParkApp.feature.driver.domain.mappers.DriverRequestMapper;
import com.equipo7.AParkApp.feature.driver.domain.mappers.DriverResponseMapper;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
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

        VehicleEntity vehicle = vehicleRepository.findByPlate(request.getPlate())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));

        DriverEntity driver = requestMapper.toEntity(request);

        driver.getVehicles().add(vehicle);
        driver.setActive(true);
        DriverEntity saved = driverRepository.save(driver);

        return responseMapper.toDto(saved);
    }

    @Override
    public void dropDriver(String dni) {

        DriverEntity driver = driverRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));

        driver.setActive(false);
        driverRepository.save(driver);
    }

    @Override
    public DriverResponse modifyDriver(String dni, DriverModifyRequest request) {

        DriverEntity driver = driverRepository.
                findByDni(dni).orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));


        if (request.getName() == null) {
            driver.setPhone(request.getPhone());

        }
        if (request.getPhone() == null) {
            driver.setName(request.getName());
        } else {
            driver.setName(request.getName());
            driver.setPhone(request.getPhone());

        }

        if (request.getName() == null && request.getPhone() == null) {
            throw new BadRequestEx("You must enter at least one field");
        }

        DriverEntity saved = driverRepository.save(driver);


        return responseMapper.toDto(saved);
    }


    @Override
    public DriverResponse addVehicleToDriver(String dni, String vehiclePlate) {

        DriverEntity driver = driverRepository.findByDni(dni).
                orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));

        VehicleEntity vehicle = vehicleRepository.
                findByPlate(vehiclePlate).orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));

        boolean exists = driver.getVehicles()
                .stream().anyMatch(v -> v.getPlate().equals(vehiclePlate));

        if (exists) {
            throw new EntityAlreadyExistsEx("Vehicle Already Exists in Driver");

        }


        driver.getVehicles().add(vehicle);
        DriverEntity saved = driverRepository.save(driver);


        return responseMapper.toDto(saved);
    }

    @Override
    public DriverResponse removeVehicleToDriver(String dni, String vehiclePlate) {

        DriverEntity driver = driverRepository.
                findByDni(dni).orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));

        VehicleEntity vehicle = vehicleRepository.
                findByPlate(vehiclePlate).orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));

        boolean exists = driver.getVehicles()
                .stream().anyMatch(v -> v.getPlate().equals(vehiclePlate));

        if (!exists) {
            throw new EntityNotFoundException("Vehicle Doesn't Exist in Driver");

        }

        driver.getVehicles().removeIf(v -> v.getPlate().equals(vehiclePlate));

        DriverEntity saved = driverRepository.save(driver);

        return responseMapper.toDto(saved);
    }

    @Override
    public List<DriverResponse> getAllDrivers() {

        List<DriverEntity> drivers = driverRepository.findAll();

        return drivers.stream().map(responseMapper::toDto).toList();
    }

    @Override
    public DriverResponse ListDriver(String dni) {

        DriverEntity driver = driverRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));

        return responseMapper.toDto(driver);
    }

    @Override
    public List<DriverResponse> getAllActiveDrivers() {

        List<DriverEntity> drivers = driverRepository.findAllByActiveTrue();

        return drivers.stream().map(responseMapper::toDto).toList();

    }
}
