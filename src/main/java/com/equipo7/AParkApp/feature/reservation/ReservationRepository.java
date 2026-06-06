package com.equipo7.AParkApp.feature.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {
    List<ReservationEntity> findByVehiclePlateContainingIgnoreCase(String plate);

    /// verificar que no haya solapamiento de fechas
    @Query("""
    SELECT COUNT(r) > 0
    FROM ReservationEntity r
    WHERE r.vehicle.id = :vehicleId
    AND r.status IN (
        com.equipo7.AParkApp.feature.reservation.ReservationStatus.RESERVED,
        com.equipo7.AParkApp.feature.reservation.ReservationStatus.ACTIVE
    )
    AND (
        r.startTime < :endTime
        AND r.endTime > :startTime
    )
""")
    boolean existsOverlappingReservation(
            @Param("vehicleId") UUID vehicleId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
