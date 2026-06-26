package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.offer.OfferEntity;
import com.equipo7.AParkApp.feature.offer.OfferRepository;
import com.equipo7.AParkApp.feature.offer.domain.dto.AcquireOfferRequest;
import com.equipo7.AParkApp.feature.parkingLot.IParkingLotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Status;
import com.equipo7.AParkApp.feature.parkingSpot.IParkingSpotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotUnavailableException;
import com.equipo7.AParkApp.feature.price.PriceService;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationUpdateRequest;
import com.equipo7.AParkApp.feature.reservation.domain.mapper.ReservationRequestMapper;
import com.equipo7.AParkApp.feature.reservation.domain.mapper.ReservationResponseMapper;
import com.equipo7.AParkApp.feature.stay.StayType;
import com.equipo7.AParkApp.feature.ticket.TicketEntity;
import com.equipo7.AParkApp.feature.ticket.TicketRepository;
import com.equipo7.AParkApp.feature.ticket.TicketStatus;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.UserRepository;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
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
    private final PriceService priceService;

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

    /// TODO AGREGAR EL CALCULO DE PRICE
    @Transactional
    @Override
    public ReservationResponseDTO save(ReservationRequestDTO dto) {
        validateVehicleAvailability(
                dto.vehicleId(),
                dto.startTime(),
                dto.endTime());
        ruleValidation(dto);

        ReservationEntity entity = createEntity(dto);

        entity.setStatus(ReservationStatus.RESERVED);

        ReservationEntity saved = repository.save(entity);

        BigDecimal amount = BigDecimal.ZERO;

        if (saved.getStayType() != StayType.Hour) {
            amount = priceService.calculateReservationPrice(saved);
        }

        TicketEntity ticket = TicketEntity.builder()
                .reservation(saved)
                .amount(amount)
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
        validateVehicleAvailabilityForUpdate(
                id,
                request.vehicleId(),
                request.startTime(),
                request.endTime());
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
        reservation.setCheckInTime(LocalDateTime.now());
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
        reservation.setCheckOutTime(LocalDateTime.now());
        if (reservation.getParkingSpot() != null) {
            reservation.getParkingSpot().setStatus(Status.FREE);
        }


        reservation.setEndTime(LocalDateTime.now());

        TicketEntity ticket =
                ticketRepository
                        .findByReservationId(reservationId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Ticket not found"));

        ticket.setAmount(
                priceService.calculateReservationPrice(
                        reservation));

        ReservationResponseDTO response = responseMapper.toDTO(
                repository.save(reservation)
        );
        ticketRepository.save(ticket);

        return response;
    }

    @Transactional
    public ReservationResponseDTO acquireOffer(
            UUID offerId,
            AcquireOfferRequest request) {

        OfferEntity offer =
                offerRepository.findByIdAndActiveTrue(offerId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Offer not found"));

        if (offer.getEndTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException(
                    "Offer has expired");
        }

        UserEntity user =
                userRepository.findById(request.userId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "User not found"));

        VehicleEntity vehicle =
                vehicleRepository.findById(request.vehicleId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Vehicle not found"));

        if (!vehicle.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException(
                    "Vehicle does not belong to user");
        }
        validateVehicleAvailability(
                vehicle.getId(),
                offer.getStartTime(),
                offer.getEndTime());
        ReservationEntity reservation =
                ReservationEntity.builder()
                        .user(user)
                        .vehicle(vehicle)
                        .offer(offer)
                        .parkingLot(offer.getParkingLot())
                        .parkingSpot(offer.getParkingSpot())
                        .stayType(offer.getStayType())
                        .startTime(offer.getStartTime())
                        .endTime(offer.getEndTime())
                        .status(ReservationStatus.RESERVED)
                        .build();

        ReservationEntity savedReservation =
                repository.save(reservation);

        BigDecimal amount = BigDecimal.ZERO;

        if (savedReservation.getStayType() != StayType.Hour) {
            amount = priceService.calculateReservationPrice(
                    savedReservation);
        }

        TicketEntity ticket =
                TicketEntity.builder()
                        .reservation(savedReservation)
                        .amount(amount)
                        .paid(BigDecimal.ZERO)
                        .status(TicketStatus.OPEN)
                        .build();

        ticketRepository.save(ticket);

        offer.setActive(false);
        offerRepository.save(offer);

        return responseMapper.toDTO(savedReservation);
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

        if (reservationRequestDTO.offerId() != null) {
            OfferEntity offer = offerRepository.findById(
                            reservationRequestDTO.offerId())
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Offer not found with id: "
                                            + reservationRequestDTO.offerId()));
            toSave.setOffer(offer);
            toSave.setStayType(offer.getStayType());

        } else {

            if (reservationRequestDTO.stayType() == null) {
                throw new IllegalArgumentException(
                        "StayType is required when no offer is selected");
            }

            toSave.setStayType(
                    reservationRequestDTO.stayType());
        }
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

    private void verifyParkingSpot(UUID parkingSpotId, LocalDateTime startTime, LocalDateTime endTime, ReservationEntity toSave) {

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

    private void validateVehicleAvailability(
            UUID vehicleId,
            LocalDateTime startTime,
            LocalDateTime endTime) {

        List<ReservationStatus> activeStatuses = List.of(
                ReservationStatus.RESERVED,
                ReservationStatus.CHECKED_IN
        );

        if (repository.existsOverlappingReservation(
                vehicleId,
                activeStatuses,
                startTime,
                endTime)) {

            throw new OverlappingReservationEx(
                    "Vehicle already has a reservation for this period");
        }
    }

    private void validateVehicleAvailabilityForUpdate(
            UUID reservationId,
            UUID vehicleId,
            LocalDateTime startTime,
            LocalDateTime endTime) {

        List<ReservationStatus> activeStatuses = List.of(
                ReservationStatus.RESERVED,
                ReservationStatus.CHECKED_IN
        );

        if (repository.existsOverlappingReservationForUpdate(
                reservationId,
                vehicleId,
                activeStatuses,
                startTime,
                endTime)) {

            throw new OverlappingReservationEx(
                    "Vehicle already has another reservation for this period");
        }
    }
}
