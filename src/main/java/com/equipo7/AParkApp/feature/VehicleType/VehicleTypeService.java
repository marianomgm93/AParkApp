package com.equipo7.AParkApp.feature.VehicleType;

import com.equipo7.AParkApp.feature.VehicleType.domain.dto.VehicleTypeDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.mappers.NewVehicleTypeMapper;
import com.equipo7.AParkApp.feature.VehicleType.domain.mappers.VehicleTypeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class VehicleTypeService implements IVehicleTypeService{
    private final VehicleTypeRepository vehicleTypeRepository;

    private final NewVehicleTypeMapper newVehicleTypeMapper;
    private final VehicleTypeMapper vehicleTypeMapper;

    @Override
    public VehicleTypeDTO save(VehicleTypeDTO vehicleTypeDTO) {
        System.out.println(vehicleTypeDTO);
        VehicleTypeEntity saved = vehicleTypeRepository.save(newVehicleTypeMapper.toEntity(vehicleTypeDTO));
        System.out.println(saved);
        return vehicleTypeMapper.toDTO(saved);
    }

    @Override
    public void delete(UUID vehicleTypeId) {
        VehicleTypeEntity vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(EntityNotFoundException::new);

        vehicleTypeRepository.delete(vehicleType);
    }

    @Override
    public VehicleTypeDTO update(UUID vehicleTypeId, VehicleTypeDTO vehicleTypeDTO) {
        VehicleTypeEntity vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(EntityNotFoundException::new);

        vehicleType.setName(vehicleTypeDTO.name());

        VehicleTypeEntity saved = vehicleTypeRepository.save(vehicleType);
        return vehicleTypeMapper.toDTO(saved);
    }

    @Override
    public VehicleTypeDTO findById(UUID vehicleTypeId) {
        return vehicleTypeRepository.findById(vehicleTypeId)
                .map(vehicleTypeMapper::toDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<VehicleTypeDTO> findAll() {
        return vehicleTypeRepository.findAll().stream().map(vehicleTypeMapper::toDTO).toList();
    }
}
