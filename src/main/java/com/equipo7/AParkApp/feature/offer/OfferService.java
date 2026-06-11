package com.equipo7.AParkApp.feature.offer;

import com.equipo7.AParkApp.feature.offer.domain.dto.OfferRequestDTO;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferResponseDTO;
import com.equipo7.AParkApp.feature.offer.domain.mapper.OfferRequestMapper;
import com.equipo7.AParkApp.feature.offer.domain.mapper.OfferResponseMapper;

import com.equipo7.AParkApp.feature.parkingLot.IParkingLotRepository;
import com.equipo7.AParkApp.feature.parkingLot.IParkingLotService;
import com.equipo7.AParkApp.feature.parkingSpot.IParkingSpotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.ParkingSpotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferService implements IOfferService{

    private final OfferRepository repository;
    private final IParkingSpotRepository spotRepository;
    private final IParkingLotRepository lotRepository;
    private final OfferRequestMapper requestMapper;
    private final OfferResponseMapper responseMapper;


    @Override
    public List<OfferResponseDTO> findAll() {
        return repository.findAll().stream().map(responseMapper::toDTO).toList();
    }
    @Override
    public List<OfferResponseDTO> findAllActive() {
        return repository.findByActiveTrue().stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public OfferResponseDTO findById(UUID id) {
        return responseMapper.toDTO(getById(id));
    }

    @Transactional
    @Override
    public OfferResponseDTO save(OfferRequestDTO request) {
        OfferEntity toSave=createEntity(request);
        return responseMapper.toDTO(repository.save(toSave));
    }

    @Transactional
    @Override
    public OfferResponseDTO update(UUID id, OfferRequestDTO requestDTO) {
        OfferEntity toUpdate=getById(id);
        OfferEntity toSave= createEntity(requestDTO);
        toSave.setId(toUpdate.getId());
        return responseMapper.toDTO(repository.save(toSave));
    }
    @Override
    public void delete(UUID id) {
        getById(id).setActive(false);
    }

    /////////// AUX
    private OfferEntity createEntity(OfferRequestDTO request) throws EntityNotFoundException{

        OfferEntity offer = requestMapper.toEntity(request);
        offer.setParkingLot(lotRepository.findById(request.parkingLotId()).orElseThrow(EntityNotFoundException::new));
        offer.setParkingSpot(spotRepository.findById(request.parkingSpotId()).orElseThrow(EntityNotFoundException::new));
        return offer;
    }
    private OfferEntity getById(UUID id) throws EntityNotFoundException{
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
