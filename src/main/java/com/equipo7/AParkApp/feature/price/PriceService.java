package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeRepository;
import com.equipo7.AParkApp.feature.price.domain.PriceDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PriceService {


    private final PriceRepository priceRepository;
    private final VehicleTypeRepository vehicleTypeRepository;


    public List<PriceDTO> findAll() {

        return priceRepository.findAll().stream().map(this::toDTO).toList();
    }


    public PriceDTO findById(UUID id) {

        PriceEntity price = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found"));

        return toDTO(price);
    }


    public PriceDTO create(PriceDTO dto) {


        VehicleTypeEntity vehicleType = vehicleTypeRepository.findById(dto.getVehicleTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle type not found"));


        PriceEntity price = PriceEntity.builder()
                .price(dto.getPrice())
                .vehicleType(vehicleType)
                .stayType(dto.getStayType())
                .build();


        return toDTO(priceRepository.save(price));
    }


    public PriceDTO update(UUID id, PriceDTO dto) {


        PriceEntity price = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found"));


        VehicleTypeEntity vehicleType = vehicleTypeRepository.findById(dto.getVehicleTypeId()).
                orElseThrow(() -> new EntityNotFoundException("Vehicle type not found"));


        price.setPrice(dto.getPrice());
        price.setVehicleType(vehicleType);
        price.setStayType(dto.getStayType());


        return toDTO(priceRepository.save(price));
    }


    public void delete(UUID id) {

        PriceEntity price = priceRepository.
                findById(id).orElseThrow(() -> new EntityNotFoundException("Price not found"));


        priceRepository.delete(price);
    }


    private PriceDTO toDTO(PriceEntity price) {

        return PriceDTO.builder()
                .id(price.getId())
                .price(price.getPrice())
                .vehicleTypeId(price.getVehicleType().getId())
                .stayType(price.getStayType())
                .build();
    }

}
