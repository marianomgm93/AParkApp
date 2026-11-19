package com.equipo7.AParkApp.feature.offer;

import com.equipo7.AParkApp.feature.offer.domain.dto.OfferRequestDTO;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface IOfferService {
    List<OfferResponseDTO> findAll();
    List<OfferResponseDTO> findAllActive(Authentication auth);
    OfferResponseDTO findById(UUID id);
    OfferResponseDTO save(OfferRequestDTO request);
    OfferResponseDTO update(UUID id, OfferRequestDTO requestDTO);
    void delete(UUID id);
}
