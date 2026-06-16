package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.common.model.exceptions.EntityAlreadyExistsEx;
import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEntity;
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

        VehicleEntity vehicle = newVehicleMapper.toEntity(newVehicleDTO);

        UserEntity user = userRepository.findById(vehicle.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        VehicleTypeEntity type = vehicleTypeRepository.findById(newVehicleDTO.getVehicleTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Type not found"));

        vehicle.setVehicleType(type);

        vehicle.setUser(user);

        VehicleEntity saved = vehicleRepository.save(vehicle);


        return vehicleMapper.toDTO(saved);
    }

    @Override
    public void delete(UUID vehicleId) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(EntityNotFoundException::new);

        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleDTO update(UUID vehicleId, NewVehicleDTO newVehicleDTO) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(EntityNotFoundException::new);


        vehicle.setPlate(newVehicleDTO.getPlate());
        vehicle.setModel(newVehicleDTO.getModel());
        vehicle.setColor(newVehicleDTO.getColor());
        vehicle.setNote(newVehicleDTO.getNote());
        vehicle.setBrand(newVehicleDTO.getBrand());


        VehicleEntity saved = vehicleRepository.save(vehicle);

        return vehicleMapper.toDTO(saved);
    }

    @Override
    public VehicleDTO findById(UUID vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(vehicleMapper::toDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream().map(vehicleMapper::toDTO).toList();
    }
}
