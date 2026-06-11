package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.offer.OfferEntity;

import com.equipo7.AParkApp.feature.offer.OfferRepository;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingLot.IParkingLotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.IParkingSpotRepository;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import com.equipo7.AParkApp.feature.reservation.domain.mapper.ReservationRequestMapper;
import com.equipo7.AParkApp.feature.reservation.domain.mapper.ReservationResponseMapper;
import com.equipo7.AParkApp.feature.user.UserRepository;
import com.equipo7.AParkApp.feature.vehicle.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService{
    /// TODO CAMBIAR POR LOS REPOSITORIOS REALES
    private final IParkingLotRepository parkingLotRepository;
    private final IParkingSpotRepository parkingSpotRepository;
    private final OfferRepository offerRepository;
    ///
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final ReservationRepository repository;
    private final ReservationRequestMapper requestMapper;
    private final ReservationResponseMapper responseMapper;



    @Override
    public List<ReservationResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(responseMapper::toDTO)
                .toList();
    }

    @Override
    public ReservationResponseDTO getById(UUID id) {
        return responseMapper.toDTO(findById(id));
    }
    @Transactional
    @Override
    public ReservationResponseDTO save(ReservationRequestDTO reservationRequestDTO) {
        //ruleValidation(reservationRequestDTO);
        ReservationEntity saved = repository.save(createEntity(reservationRequestDTO));
        return responseMapper.toDTO(saved);
    }

    @Transactional
    @Override
    public ReservationResponseDTO update(UUID id, ReservationRequestDTO reservationRequestDTO) {
        ReservationEntity toErase= findById(id);
        ReservationEntity toSave= createEntity(reservationRequestDTO);
        toSave.setId(toErase.getId());
        return responseMapper.toDTO(repository.save(toSave));
    }
    @Transactional
    public void delete(UUID id){
        findById(id).setStatus(ReservationStatus.CANCELLED);
    }
    public List<ReservationResponseDTO> findByPlate(String plate){
        return repository.findByVehiclePlateContainingIgnoreCase(plate)
                .stream().map(responseMapper::toDTO)
                .toList();
    }


    /// AUX
    /*
    private boolean ruleValidation(ReservationRequestDTO reservationRequestDTO) throws OverlappingReservationEx{
         if(repository.existsOverlappingReservation(
                reservationRequestDTO.vehicleId(),
                reservationRequestDTO.startTime(),
                reservationRequestDTO.endTime())){
             throw new OverlappingReservationEx("The vehicle has another reservation in that period");
         }
         return false;
    }

     */
    private ReservationEntity createEntity(ReservationRequestDTO reservationRequestDTO) {

        ReservationEntity toSave = requestMapper.toEntity(reservationRequestDTO);
        toSave.setParkingLot(parkingLotRepository.findById(reservationRequestDTO.parkingLotId())
                .orElseThrow(EntityNotFoundException::new));
        toSave.setParkingSpot(reservationRequestDTO.parkingSpotId() == null ? null :
                parkingSpotRepository.findById(reservationRequestDTO.parkingSpotId())
                        .orElseThrow(EntityNotFoundException::new));
        toSave.setOffer(offerRepository.findById(reservationRequestDTO.offerId())
                .orElseThrow(EntityNotFoundException::new));
        toSave.setVehicle(vehicleRepository.findById(reservationRequestDTO.vehicleId())
                .orElseThrow(EntityNotFoundException::new));
        toSave.setUser(userRepository.findById(reservationRequestDTO.userId())
                .orElseThrow(EntityNotFoundException::new));
        return toSave;
    }

    private ReservationEntity findById(UUID id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
