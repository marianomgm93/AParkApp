package com.equipo7.AParkApp.feature.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, UUID> {

    boolean existsByDni(String dni);

    Optional<DriverEntity> findByDni(String dni);

    List<DriverEntity> findAllByActiveTrue();
}
