package com.equipo7.AParkApp.feature.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, UUID> {
    @Query("""
    SELECT o FROM OfferEntity o
    WHERE o.active = true
      AND (
        o.parkingLot.owner.credentials.username = :identifier
        OR o.parkingLot.owner.email = :identifier
      )
    """)
    List<OfferEntity> findByOwnerIdentifierAndActiveTrue(@Param("identifier") String identifier);
    List<OfferEntity> findByActiveTrue();
    Optional<OfferEntity> findByIdAndActiveTrue(UUID id);
}
