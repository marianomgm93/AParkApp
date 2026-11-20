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
                .status(ticketResponseDTO.status())
                .build();
    }

    @Override
    public TicketResponseDTO toDTO(TicketEntity ticketEntity) {
        String parkingLotName = null;
        if (ticketEntity.getReservation() != null && ticketEntity.getReservation().getParkingLot() != null) {
            parkingLotName = ticketEntity.getReservation().getParkingLot().getName();
        }
        return new TicketResponseDTO(
                ticketEntity.getId(),
                ticketEntity.getReservation() != null ? ticketEntity.getReservation().getId() : null,
                ticketEntity.getAmount(),
                ticketEntity.getPaid(),
                ticketEntity.getTimeStamp(),
                ticketEntity.getNote(),
                parkingLotName,
                ticketEntity.getStatus()
        );
    }
}
