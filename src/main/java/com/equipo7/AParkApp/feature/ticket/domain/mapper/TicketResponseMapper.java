package com.equipo7.AParkApp.feature.ticket.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.ticket.TicketEntity;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketRequestDTO;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TicketResponseMapper implements IMapper<TicketEntity, TicketResponseDTO> {

    @Override
    public TicketEntity toEntity(TicketResponseDTO ticketResponseDTO) {
        return TicketEntity.builder()
                .amount(ticketResponseDTO.amount())
                .paid(ticketResponseDTO.paid())
                .timeStamp(ticketResponseDTO.timeStamp())
                .note(ticketResponseDTO.note())
                .build();
    }

    @Override
    public TicketResponseDTO toDTO(TicketEntity ticketEntity) {
        return new TicketResponseDTO(
                ticketEntity.getId(),
                ticketEntity.getReservation().getId(),
                ticketEntity.getAmount(),
                ticketEntity.getPaid(),
                ticketEntity.getTimeStamp(),
                ticketEntity.getNote()
        );
    }
}
