package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.offer.OfferEntity;

import com.equipo7.AParkApp.feature.offer.OfferRepository;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingLot.IParkingLotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Status;
import com.equipo7.AParkApp.feature.parkingSpot.IParkingSpotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotUnavailableException;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationUpdateRequest;
import com.equipo7.AParkApp.feature.reservation.domain.mapper.ReservationRequestMapper;
import com.equipo7.AParkApp.feature.reservation.domain.mapper.ReservationResponseMapper;
import com.equipo7.AParkApp.feature.ticket.TicketEntity;
import com.equipo7.AParkApp.feature.ticket.TicketRepository;
import com.equipo7.AParkApp.feature.ticket.TicketStatus;
import com.equipo7.AParkApp.feature.user.UserRepository;
import com.equipo7.AParkApp.feature.vehicle.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {
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
    private final TicketRepository ticketRepository;

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
    public ReservationResponseDTO save(ReservationRequestDTO dto) {

        ruleValidation(dto);

        ReservationEntity entity = createEntity(dto);

        entity.setStatus(ReservationStatus.RESERVED);

        ReservationEntity saved = repository.save(entity);

        TicketEntity ticket = TicketEntity.builder()
                .reservation(saved)
                .amount(BigDecimal.ZERO)
                .paid(BigDecimal.ZERO)
                .status(TicketStatus.OPEN)
                .build();

        ticketRepository.save(ticket);

        return responseMapper.toDTO(saved);
    }

    @Transactional
    @Override
    public ReservationResponseDTO update(UUID id, ReservationUpdateRequest request) {
        ReservationEntity toSave = findById(id);
        toSave.setVehicle(vehicleRepository.findById(request.vehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id "
                        + request.vehicleId())));
        toSave.setStartTime(request.startTime());
        toSave.setEndTime(request.endTime());
        verifyParkingSpotForUpdate(id, request.parkingSpotId(), request.startTime(), request.endTime(), toSave);
        return responseMapper.toDTO(repository.save(toSave));
    }

    @Transactional
    public ReservationResponseDTO cancel(UUID id) {

        ReservationEntity reservation = findById(id);

        if (reservation.getStatus() == ReservationStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed reservation cannot be cancelled");
        }

        if (reservation.getStatus() == ReservationStatus.CHECKED_IN
                && reservation.getParkingSpot() != null) {

            reservation.getParkingSpot().setStatus(Status.FREE);
        }

        reservation.setStatus(ReservationStatus.CANCELLED);

        return responseMapper.toDTO(repository.save(reservation));
    }

    public List<ReservationResponseDTO> findByPlate(String plate) {
        return repository.findByVehiclePlateContainingIgnoreCase(plate)
                .stream().map(responseMapper::toDTO)
                .toList();
    }

    @Transactional
    public ReservationResponseDTO checkIn(UUID reservationId) {

        ReservationEntity reservation = findById(reservationId);

        if (reservation.getStatus() != ReservationStatus.RESERVED) {
            throw new IllegalStateException(
                    "Only RESERVED reservations can check in");
        }

        reservation.setStatus(ReservationStatus.CHECKED_IN);

        if (reservation.getParkingSpot() != null) {
            reservation.getParkingSpot().setStatus(Status.OCCUPIED);
        }

        return responseMapper.toDTO(
                repository.save(reservation)
        );
    }

    @Transactional
    public ReservationResponseDTO checkOut(UUID reservationId) {

        ReservationEntity reservation = findById(reservationId);

        if (reservation.getStatus() != ReservationStatus.CHECKED_IN) {
            throw new IllegalStateException(
                    "Only CHECKED_IN reservations can check out");
        }

        reservation.setStatus(ReservationStatus.COMPLETED);

        if (reservation.getParkingSpot() != null) {
            reservation.getParkingSpot().setStatus(Status.FREE);
        }

        return responseMapper.toDTO(
                repository.save(reservation)
        );
    }


    /// AUX
    private void ruleValidation(ReservationRequestDTO dto) {

        if (repository.existsOverlappingReservation(
                dto.vehicleId(),
                List.of(
                        ReservationStatus.RESERVED,
                        ReservationStatus.CHECKED_IN
                ),
                dto.startTime(),
                dto.endTime())) {

            throw new OverlappingReservationEx(
                    "The vehicle already has a reservation in that period");
        }
    }

    private ReservationEntity createEntity(ReservationRequestDTO reservationRequestDTO) {

        ReservationEntity toSave = requestMapper.toEntity(reservationRequestDTO);

        toSave.setParkingLot(
                parkingLotRepository.findById(reservationRequestDTO.parkingLotId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Parking lot not found with id: " + reservationRequestDTO.parkingLotId()))
        );

        verifyParkingSpot(reservationRequestDTO.parkingSpotId(),
                reservationRequestDTO.startTime(), reservationRequestDTO.endTime(),
                toSave);

        toSave.setOffer(
                offerRepository.findById(reservationRequestDTO.offerId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Offer not found with id: " + reservationRequestDTO.offerId()))
        );

        toSave.setVehicle(
                vehicleRepository.findById(reservationRequestDTO.vehicleId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Vehicle not found with id: " + reservationRequestDTO.vehicleId()))
        );

        toSave.setUser(
                userRepository.findById(reservationRequestDTO.userId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "User not found with id: " + reservationRequestDTO.userId()))
        );

        return toSave;
    }

    private ReservationEntity findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Reservation not found with id: " + id));
    }

    private void verifyParkingSpot(
            UUID parkingSpotId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            ReservationEntity toSave) {

        if (parkingSpotId != null) {


            ParkingSpotEntity parking =
                    parkingSpotRepository.findByIdAndActiveTrue(parkingSpotId)
                            .orElseThrow(() ->
                                    new EntityNotFoundException(
                                            "Parking spot not found"));

            if (!parkingSpotRepository.isAvailable(
                    parkingSpotId,
                    toSave.getParkingLot().getId(),
                    startTime,
                    endTime)) {

                throw new ParkingSpotUnavailableException(
                        "The selected parking spot is not available for the requested period");
            }

            toSave.setParkingSpot(parking);
        }
    }

    private void verifyParkingSpotForUpdate(
            UUID reservationId,
            UUID parkingSpotId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            ReservationEntity reservation) {

        if (parkingSpotId == null) {
            reservation.setParkingSpot(null);
            return;
        }

        ParkingSpotEntity parkingSpot =
                parkingSpotRepository.findByIdAndActiveTrue(parkingSpotId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Parking spot not found with id "
                                                + parkingSpotId));

        boolean available = parkingSpotRepository.isAvailableForUpdate(
                reservationId,
                parkingSpotId,
                reservation.getParkingLot().getId(),
                startTime,
                endTime
        );

        if (!available) {
            throw new ParkingSpotUnavailableException(
                    "The selected parking spot is not available for the requested period");
        }

        reservation.setParkingSpot(parkingSpot);
    }
}
