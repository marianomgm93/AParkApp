package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.common.model.exceptions.EntityAlreadyExistsEx;
import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeRepository;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.UserRepository;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.mappers.NewVehicleMapper;
import com.equipo7.AParkApp.feature.vehicle.domain.mappers.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {

    private final VehicleRepository vehicleRepository;

    private final NewVehicleMapper newVehicleMapper;
    private final VehicleMapper vehicleMapper;
    private final UserRepository userRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleDTO save(NewVehicleDTO newVehicleDTO) {

        if (vehicleRepository.existsByPlate(newVehicleDTO.getPlate())) {

            throw new EntityAlreadyExistsEx("Vehicle with plate " + newVehicleDTO.getPlate() + " already exists");

        }

        VehicleEntity vehicle = creationEntity(newVehicleDTO);
        VehicleEntity saved = vehicleRepository.save(vehicle);

        return vehicleMapper.toDTO(saved);
    }

    @Override
    public void delete(UUID vehicleId) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleDTO update(UUID vehicleId, NewVehicleDTO dto) {

        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        if (!vehicle.getPlate().equals(dto.getPlate())
                && vehicleRepository.existsByPlate(dto.getPlate())) {

            throw new EntityAlreadyExistsEx("Vehicle with plate " + dto.getPlate() + " already exists");
        }

        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        vehicle.setPlate(dto.getPlate());
        vehicle.setModel(dto.getModel());
        vehicle.setColor(dto.getColor());
        vehicle.setNote(dto.getNote());
        vehicle.setBrand(dto.getBrand());
        vehicle.setUser(user);
        vehicle.setVehicleType(dto.getVehicleType());

        VehicleEntity saved = vehicleRepository.save(vehicle);

        return vehicleMapper.toDTO(saved);
    }

    @Override
    public VehicleDTO findById(UUID vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(vehicleMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
    }

    @Override
    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream().map(vehicleMapper::toDTO).toList();
    }

    VehicleEntity creationEntity(NewVehicleDTO newVehicleDTO) {
        VehicleEntity vehicle = newVehicleMapper.toEntity(newVehicleDTO);

        vehicle.setUser(userRepository.findById(newVehicleDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));

        return vehicle;
    }
}
