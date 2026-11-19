package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketRequestDTO;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketResponseDTO;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ITicketService {
    List<TicketResponseDTO> findAll();
    List<TicketResponseDTO> findAll(Authentication auth);
    TicketResponseDTO findById(UUID id);
    List<TicketResponseDTO> findByperiod(LocalDateTime startDate, LocalDateTime endDate);
    List<TicketResponseDTO> findToday();
    List<TicketResponseDTO> findToday(Authentication auth);
    TicketResponseDTO create(TicketRequestDTO request);
    TicketResponseDTO addPay(com.equipo7.AParkApp.feature.ticket.domain.dto.PaidRequestDTO request);
    TicketResponseDTO addPay(com.equipo7.AParkApp.feature.ticket.domain.dto.PaidRequestDTO request, Authentication auth);
    void delete(UUID id);
    void delete(UUID id, Authentication auth);
}
