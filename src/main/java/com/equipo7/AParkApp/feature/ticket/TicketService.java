package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.parkingSpot.IParkingSpotRepository;
import com.equipo7.AParkApp.feature.reservation.ReservationRepository;
import com.equipo7.AParkApp.feature.ticket.domain.dto.PaidRequestDTO;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketRequestDTO;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketResponseDTO;
import com.equipo7.AParkApp.feature.ticket.domain.mapper.TicketRequestMapper;
import com.equipo7.AParkApp.feature.ticket.domain.mapper.TicketResponseMapper;
import com.equipo7.AParkApp.feature.ticket.exception.InvalidAmountException;
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
public class TicketService implements ITicketService {
    private final TicketRepository repository;
    private final TicketRequestMapper requestMapper;
    private final TicketResponseMapper responseMapper;
    private final ReservationRepository reservationRepository;

    @Override
    public List<TicketResponseDTO> findAll() {
        return repository.findAll().stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public TicketResponseDTO findById(UUID id) {
        return responseMapper.toDTO(getById(id));
    }

    @Override
    public List<TicketResponseDTO> findByperiod(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByTimeStampBetween(startDate, endDate).stream().map(responseMapper::toDTO).toList();
    }

    public List<TicketResponseDTO> findToday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime initialDay = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        return repository.findByTimeStampBetween(initialDay, now).stream().map(responseMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public TicketResponseDTO create(TicketRequestDTO request) {
        return responseMapper.toDTO(repository.save(createEntity(request)));
    }

    @Transactional
    public TicketResponseDTO addPay(PaidRequestDTO paidRequestDTO) {
        TicketEntity toModify = getById(paidRequestDTO.id());
        BigDecimal totalPay = paidRequestDTO.pay().add(toModify.getPaid());
        if (totalPay.compareTo(toModify.getAmount()) > 0 )
            throw new InvalidAmountException("Paided amount is bigger than total Amount");
        toModify.setPaid(totalPay);
        if (toModify.getAmount().compareTo(totalPay) == 0)
            toModify.setStatus(TicketStatus.PAID);
        return responseMapper.toDTO(repository.save(toModify));
    }

    @Override
    public void delete(UUID id) {
        getById(id).setStatus(TicketStatus.CANCELLED);
    }

    /// ////////AUX
    private TicketEntity createEntity(TicketRequestDTO request) {
        TicketEntity toSave = requestMapper.toEntity(request);
        toSave.setReservation(reservationRepository.findById(request.reservationId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + request.reservationId())));
        return toSave;
    }

    private TicketEntity getById(UUID id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Ticket not found with id: " + id));
    }
}
