package com.equipo7.AParkApp.feature.parkingSpot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingLot.IParkingLotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotRequest;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotResponse;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers.ParkingSpotRequestMapper;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers.ParkingSpotResponseMapper;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Status;
import com.equipo7.AParkApp.feature.reservation.ReservationRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ParkingSpotService implements IParkingSpotService {

    private final IParkingSpotRepository repository;
    private final IParkingLotRepository parkingLotRepository;
    private final ParkingSpotRequestMapper requestMapper;
    private final ParkingSpotResponseMapper responseMapper;

    @Override
    public ParkingSpotResponse createParkingSpot(ParkingSpotRequest request) {

        ParkingLotEntity parkingLot =
                parkingLotRepository.
                        findByIdAndActiveTrue(
                                request.getParkingLotId()).orElseThrow
                                (() -> new EntityNotFoundException("Parking Lot Not Found"));

        ParkingSpotEntity entity = requestMapper.toEntity(request);

        entity.setParkingLot(parkingLot);
        entity.setStatus(Status.FREE);
        entity.setActive(true);

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
        entity.setStatus(Status.FREE);
        repository.save(entity);
    }

    @Override
    public void restoreParkingSpot(UUID id) {

        ParkingSpotEntity entity=repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        entity.setActive(true);
        entity.setStatus(Status.FREE);

        repository.save(entity);
    }

    @Override
    public void occupy(UUID id) {
        ParkingSpotEntity entity=repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        entity.setActive(true);
        entity.setStatus(Status.OCCUPIED);
        repository.save(entity);

    }

    @Override
    public void release(UUID id) {
        ParkingSpotEntity entity=repository.findByIdAndActiveTrue(id).
                orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        entity.setActive(false);
        entity.setStatus(Status.FREE);
        repository.save(entity);
    }
    public List<ParkingSpotResponse> findAvailableSpots(
            UUID parkingLotId,
            LocalDateTime startTime,
            LocalDateTime endTime) {

        return repository.findAvailableSpots(
                        parkingLotId,
                        startTime,
                        endTime)
                .stream()
                .map(responseMapper::toDto)
                .toList();
    }

}
