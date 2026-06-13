package com.equipo7.AParkApp.feature.ticket.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.ticket.TicketEntity;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TicketRequestMapper implements IMapper<TicketEntity, TicketRequestDTO> {

    @Override
    public TicketEntity toEntity(TicketRequestDTO ticketRequestDTO) {
        return TicketEntity.builder()
                .amount(ticketRequestDTO.amount())
                .paid(ticketRequestDTO.paid())
                .timeStamp(ticketRequestDTO.timeStamp())
                .note(ticketRequestDTO.note())
                .build();
    }

    @Override
    public TicketRequestDTO toDTO(TicketEntity ticketEntity) {
        return new TicketRequestDTO(
                ticketEntity.getReservation().getId(),
                ticketEntity.getAmount(),
                ticketEntity.getPaid(),
                ticketEntity.getTimeStamp(),
                ticketEntity.getNote()
        );
    }
}
