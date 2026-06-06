package com.equipo7.AParkApp.feature.reservation.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.reservation.ReservationEntity;
import com.equipo7.AParkApp.feature.reservation.ReservationStatus;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReservationResponseMapper implements IMapper<ReservationEntity, ReservationResponseDTO> {
    @Override
    public ReservationEntity toEntity(ReservationResponseDTO reservationResponseDTO) {
        return ReservationEntity.builder()
                .startTime(reservationResponseDTO.startTime())
                .endTime(reservationResponseDTO.endTime())
                .status(ReservationStatus.valueOf(reservationResponseDTO.status()))
                .build();
    }

    @Override
    public ReservationResponseDTO toDTO(ReservationEntity reservationEntity) {
        return new ReservationResponseDTO(
                reservationEntity.getStartTime(),
                reservationEntity.getEndTime(),
                reservationEntity.getParkingLot().getId(),
                reservationEntity.getParkingSpot().getId(),
                reservationEntity.getVehicle().getId(),
                reservationEntity.getOffer().getId(),
                reservationEntity.getUser().getId(),
                reservationEntity.getStatus().toString()
        );
    }
}
