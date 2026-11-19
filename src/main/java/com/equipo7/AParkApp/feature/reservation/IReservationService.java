package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.auth.dto.NewAccountRequest;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationUpdateRequest;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface IReservationService {
    List<ReservationResponseDTO> getAll(Authentication auth);
    ReservationResponseDTO getById(UUID id);
    ReservationResponseDTO save(ReservationRequestDTO reservationRequestDTO);
    ReservationResponseDTO update(UUID id, ReservationUpdateRequest request);
}
