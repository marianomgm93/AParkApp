package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.mappers.NewVehicleMapper;
import com.equipo7.AParkApp.feature.vehicle.domain.mappers.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor

public class VehicleService implements IVehicleService{

    private final VehicleRepository vehicleRepository;

    private final NewVehicleMapper newVehicleMapper;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleDTO save(NewVehicleDTO newVehicleDTO) {
        System.out.println(newVehicleDTO);
        VehicleEntity saved = vehicleRepository.save(newVehicleMapper.toEntity(newVehicleDTO));
        System.out.println(saved);
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


        vehicle.setPlate(newVehicleDTO.plate());
        vehicle.setModel(newVehicleDTO.model());
        vehicle.setColor(newVehicleDTO.color());
        vehicle.setNote(newVehicleDTO.note());
        vehicle.setBrand(newVehicleDTO.brand());


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
