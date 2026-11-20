package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.auth.credentials.CredentialsEntity;
import com.equipo7.AParkApp.feature.auth.credentials.CredentialsRepository;
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
import org.springframework.security.core.Authentication;
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
    private final CredentialsRepository credentialsRepository;

    @Override
    public List<TicketResponseDTO> findAll() {
        return repository.findAll().stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public List<TicketResponseDTO> findAll(Authentication auth) {
        if (auth == null) {
            return findAll();
        }
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return findAll();
        }

        CredentialsEntity credentials = credentialsRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + auth.getName()));

        if (credentials.getUsuario() == null) {
            throw new EntityNotFoundException("No user profile associated with: " + auth.getName());
        }

        UUID userId = credentials.getUsuario().getId();
        boolean isOwner = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_OWNER") || a.getAuthority().equals("ROLE_EMPLOYEE"));
        if (isOwner) {
            return repository.findByReservationParkingLotOwnerId(userId).stream().map(responseMapper::toDTO).toList();
        }

        return repository.findByReservationUserId(userId).stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public TicketResponseDTO findById(UUID id) {
        return responseMapper.toDTO(getById(id));
    }

    @Override
    public List<TicketResponseDTO> findByperiod(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByTimeStampBetween(startDate, endDate).stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public List<TicketResponseDTO> findToday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime initialDay = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        return repository.findByTimeStampBetween(initialDay, now).stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public List<TicketResponseDTO> findToday(Authentication auth) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime initialDay = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);

        if (auth == null) {
            return findToday();
        }
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return findToday();
        }

        CredentialsEntity credentials = credentialsRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + auth.getName()));

        if (credentials.getUsuario() == null) {
            throw new EntityNotFoundException("No user profile associated with: " + auth.getName());
        }

        UUID userId = credentials.getUsuario().getId();
        boolean isOwner = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_OWNER") || a.getAuthority().equals("ROLE_EMPLOYEE"));
        if (isOwner) {
            return repository.findByReservationParkingLotOwnerIdAndTimeStampBetween(userId, initialDay, now).stream().map(responseMapper::toDTO).toList();
        }

        return repository.findByReservationUserIdAndTimeStampBetween(userId, initialDay, now).stream().map(responseMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public TicketResponseDTO create(TicketRequestDTO request) {
        return responseMapper.toDTO(repository.save(createEntity(request)));
    }

    @Override
    @Transactional
    public TicketResponseDTO addPay(PaidRequestDTO paidRequestDTO) {
        return addPay(paidRequestDTO, null);
    }

    @Override
    @Transactional
    public TicketResponseDTO addPay(PaidRequestDTO paidRequestDTO, Authentication auth) {
        TicketEntity toModify = getById(paidRequestDTO.id());
        validateOwnerAccess(toModify, auth);

        if (toModify.getStatus() == TicketStatus.CANCELLED) {
            throw new InvalidAmountException("No se puede pagar un ticket cancelado");
        }
        if (toModify.getStatus() == TicketStatus.PAID) {
            throw new InvalidAmountException("El ticket ya se encuentra pagado");
        }

        BigDecimal totalPay = paidRequestDTO.pay().add(toModify.getPaid());
        if (totalPay.compareTo(toModify.getAmount()) > 0) {
            throw new InvalidAmountException("Paid amount is bigger than total Amount");
        }

        toModify.setPaid(totalPay);
        if (toModify.getAmount().compareTo(totalPay) == 0) {
            toModify.setStatus(TicketStatus.PAID);
        } else if (totalPay.compareTo(BigDecimal.ZERO) > 0) {
            toModify.setStatus(TicketStatus.PARTIALLY_PAID);
        }

        return responseMapper.toDTO(repository.save(toModify));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        delete(id, null);
    }

    @Override
    @Transactional
    public void delete(UUID id, Authentication auth) {
        TicketEntity toDelete = getById(id);
        validateOwnerAccess(toDelete, auth);
        if (toDelete.getStatus() == TicketStatus.PAID) {
            throw new IllegalStateException("No se puede cancelar un ticket pagado");
        }
        toDelete.setStatus(TicketStatus.CANCELLED);
        repository.save(toDelete);
    }

    private void validateOwnerAccess(TicketEntity ticket, Authentication auth) {
        if (auth == null) {
            return;
        }
        boolean isOwner = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_OWNER") || a.getAuthority().equals("ROLE_EMPLOYEE"));
        if (isOwner) {
            CredentialsEntity credentials = credentialsRepository.findByUsername(auth.getName())
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + auth.getName()));
            if (credentials.getUsuario() != null) {
                UUID currentUserId = credentials.getUsuario().getId();
                if (ticket.getReservation() != null &&
                        ticket.getReservation().getParkingLot() != null &&
                        ticket.getReservation().getParkingLot().getOwner() != null &&
                        !ticket.getReservation().getParkingLot().getOwner().getId().equals(currentUserId)) {
                    throw new org.springframework.security.access.AccessDeniedException("No tienes permiso para modificar tickets de otro estacionamiento");
                }
            }
        }
    }

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
