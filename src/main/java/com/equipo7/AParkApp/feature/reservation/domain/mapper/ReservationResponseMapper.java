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
                reservationEntity.getId(),
                reservationEntity.getStartTime(),
                reservationEntity.getEndTime(),
                reservationEntity.getParkingLot() != null ? reservationEntity.getParkingLot().getId() : null,
                reservationEntity.getParkingLot() != null ? reservationEntity.getParkingLot().getName() : null,
                reservationEntity.getParkingSpot() != null ? reservationEntity.getParkingSpot().getId() : null,
                reservationEntity.getVehicle() != null ? reservationEntity.getVehicle().getId() : null,
                reservationEntity.getVehicle() != null ? reservationEntity.getVehicle().getPlate() : null,
                reservationEntity.getOffer() != null ? reservationEntity.getOffer().getId() : null,
                reservationEntity.getUser() != null ? reservationEntity.getUser().getId() : null,
                reservationEntity.getUser() != null ? reservationEntity.getUser().getName() : null,
                reservationEntity.getStayType().toString(),
                reservationEntity.getStatus().toString()
        );
    }
}
