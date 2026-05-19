package com.equipo7.AParkApp.feature.parkingSpot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingLot.IParkingLotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotRequest;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotResponse;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers.ParkingSpotRequestMapper;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers.ParkingSpotResponseMapper;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotService implements IParkingSpotService {

    @Autowired
    private IParkingSpotRepository repository;

    @Autowired
    private IParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingSpotRequestMapper requestMapper;
    @Autowired
    private ParkingSpotResponseMapper responseMapper;


    @Override
    public ParkingSpotResponse createParkingSpot(ParkingSpotRequest request) {

        ParkingLotEntity parkingLot =
                parkingLotRepository.
                        findByIdAndActiveTrue(
                                request.getParkingLotId()).orElseThrow
                                (() -> new EntityNotFoundException("Parking Lot Not Found"));

        ParkingSpotEntity entity=requestMapper.toEntity(request);

        entity.setParkingLot(parkingLot);
        entity.setStatus(true);

        ParkingSpotEntity savedEntity=repository.save(entity);

        return responseMapper.toDto(savedEntity);
    }

    @Override
    public List<ParkingSpotResponse> getAllParkingSpots() {
        List<ParkingSpotEntity> spots=repository.findAll();

        return spots.stream().map(responseMapper::toDto).toList();
    }

    @Override
    public ParkingSpotResponse getParkingSpotById(UUID id) {
        ParkingSpotEntity entity=repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking spot Not Found"));

        return responseMapper.toDto(entity);
    }

    @Override
    public ParkingSpotResponse updateParkingSpot(UUID id, ParkingSpotRequest request) {

        ParkingSpotEntity entity = repository.findByIdAndActiveTrue(id)
                        .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));


        ParkingLotEntity parkingLot = parkingLotRepository.
                findByIdAndActiveTrue(request.getParkingLotId())
                        .orElseThrow(() -> new EntityNotFoundException("Cochera no encontrada"));

        entity.setNumber(request.getNumber());
        entity.setStatus(request.getStatus());
        entity.setParkingLot(parkingLot);
        entity.setName(request.getName());
        ParkingSpotEntity updated = repository.save(entity);

        return responseMapper.toDto(updated);
    }

    @Override
    public void deleteParkingSpotById(UUID id) {
        ParkingSpotEntity entity=repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        entity.setActive(false);
        repository.save(entity);
    }

    @Override
    public void restoreParkingSpot(UUID id) {

        ParkingSpotEntity entity=repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        entity.setActive(true);
        repository.save(entity);
    }

    @Override
    public void occupy(UUID id) {
        ParkingSpotEntity entity=repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        entity.setStatus(true);
        repository.save(entity);

    }

    @Override
    public void release(UUID id) {
        ParkingSpotEntity entity=repository.findByIdAndActiveTrue(id).
                orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        entity.setStatus(false);
        repository.save(entity);
    }


}
