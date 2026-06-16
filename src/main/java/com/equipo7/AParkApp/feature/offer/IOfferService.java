package com.equipo7.AParkApp.feature.offer;

import com.equipo7.AParkApp.feature.offer.domain.dto.OfferRequestDTO;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IOfferService {
    List<OfferResponseDTO> findAll();
    List<OfferResponseDTO> findAllActive();
    OfferResponseDTO findById(UUID id);
    OfferResponseDTO save(OfferRequestDTO request);
    OfferResponseDTO update(UUID id, OfferRequestDTO requestDTO);
    void delete(UUID id);
}
