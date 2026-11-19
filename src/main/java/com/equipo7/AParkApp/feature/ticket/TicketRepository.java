package com.equipo7.AParkApp.feature.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    List<TicketEntity> findByTimeStampBetween(
            LocalDateTime startDate,
            LocalDateTime endDate);
    Optional<TicketEntity> findByReservationId(UUID reservationId);
    List<TicketEntity> findByReservationParkingLotOwnerId(UUID ownerId);
    List<TicketEntity> findByReservationParkingLotOwnerIdAndTimeStampBetween(
            UUID ownerId,
            LocalDateTime startDate,
            LocalDateTime endDate);
    List<TicketEntity> findByReservationUserId(UUID userId);
    List<TicketEntity> findByReservationUserIdAndTimeStampBetween(
            UUID userId,
            LocalDateTime startDate,
            LocalDateTime endDate);
}
