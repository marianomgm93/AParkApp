package com.equipo7.AParkApp.feature.parkingSpot;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IParkingSpotRepository extends JpaRepository<ParkingSpotEntity, UUID> {

    Optional<ParkingSpotEntity> findByIdAndActiveTrue(UUID id);

    List<ParkingSpotEntity> findByParkingLotIdAndActiveTrue(UUID parkingLotId);

    @Query("""
            SELECT ps
            FROM ParkingSpotEntity ps
            WHERE ps.parkingLot.id = :parkingLotId
            AND ps.active = true
            AND NOT EXISTS (
                SELECT r
                FROM ReservationEntity r
                WHERE r.parkingSpot = ps
                AND r.status IN (
                    com.equipo7.AParkApp.feature.reservation.ReservationStatus.RESERVED,
                    com.equipo7.AParkApp.feature.reservation.ReservationStatus.CHECKED_IN
                )
                AND r.startTime < :endTime
                AND r.endTime > :startTime
            )
            """)
    List<ParkingSpotEntity> findAvailableSpots(
            @Param("parkingLotId") UUID parkingLotId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    @Query("""
            SELECT COUNT(ps) > 0
            FROM ParkingSpotEntity ps
            WHERE ps.id = :parkingSpotId
            AND ps.parkingLot.id = :parkingLotId
            AND ps.active = true
            AND NOT EXISTS (
                SELECT r
                FROM ReservationEntity r
                WHERE r.parkingSpot = ps
                AND r.status IN (
                    com.equipo7.AParkApp.feature.reservation.ReservationStatus.RESERVED,
                    com.equipo7.AParkApp.feature.reservation.ReservationStatus.CHECKED_IN
                )
                AND r.startTime < :endTime
                AND r.endTime > :startTime
            )
            """)
    boolean isAvailable(
            @Param("parkingSpotId") UUID parkingSpotId,
            @Param("parkingLotId") UUID parkingLotId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    @Query("""
            SELECT COUNT(ps) > 0
            FROM ParkingSpotEntity ps
            WHERE ps.id = :parkingSpotId
            AND ps.parkingLot.id = :parkingLotId
            AND ps.active = true
            AND NOT EXISTS (
                SELECT r
                FROM ReservationEntity r
                WHERE r.parkingSpot = ps
                AND r.id <> :reservationId
                AND r.status IN (
                    com.equipo7.AParkApp.feature.reservation.ReservationStatus.RESERVED,
                    com.equipo7.AParkApp.feature.reservation.ReservationStatus.CHECKED_IN
                )
                AND r.startTime < :endTime
                AND r.endTime > :startTime
            )
            """)
    boolean isAvailableForUpdate(
            @Param("reservationId") UUID reservationId,
            @Param("parkingSpotId") UUID parkingSpotId,
            @Param("parkingLotId") UUID parkingLotId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
