package com.equipo7.AParkApp.feature.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, UUID> {

    List<OfferEntity> findByActiveTrue();
}
