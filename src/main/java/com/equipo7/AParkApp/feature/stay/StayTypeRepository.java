package com.equipo7.AParkApp.feature.stay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StayTypeRepository extends JpaRepository<StayTypeEntity, UUID> {
}
