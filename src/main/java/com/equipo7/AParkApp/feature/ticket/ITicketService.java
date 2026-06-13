package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketRequestDTO;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ITicketService {
    List<TicketResponseDTO> findAll();
    TicketResponseDTO findById(UUID id);
    //List<TicketResponseDTO> findToday();
    //List<TicketResponseDTO> findByDate(LocalDateTime date);
    List<TicketResponseDTO> findByperiod(LocalDateTime startDate, LocalDateTime endDate);
    TicketResponseDTO create(TicketRequestDTO request);
    void delete(UUID id);
}
