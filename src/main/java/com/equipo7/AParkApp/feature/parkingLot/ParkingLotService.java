package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.address.AddressRepository;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotClientView;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import com.equipo7.AParkApp.feature.parkingLot.Domain.Mappers.ParkingLotRequestMapper;
import com.equipo7.AParkApp.feature.parkingLot.Domain.Mappers.ParkingLotResponseMapper;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingLotService implements IParkingLotService {

    private final IParkingLotRepository repository;

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    private final ParkingLotRequestMapper requestMapper;

    private final ParkingLotResponseMapper responseMapper;


    @Override
    public ParkingLotResponse create(ParkingLotRequest request) {

        ParkingLotEntity parkingLotEntity = requestMapper.toEntity(request);
        parkingLotEntity.setOwner(userRepository.findById(request.getOwnerId()).orElseThrow(() -> new EntityNotFoundException("Owner not found")));
        parkingLotEntity.setAddress(addressRepository.findById(request.getAddressId()).orElseThrow(() -> new EntityNotFoundException("Address not found")));
        parkingLotEntity.setActive(true);

        ParkingLotEntity newParkingLotEntity = repository.save(parkingLotEntity);


        return responseMapper.toDTO(newParkingLotEntity);
    }

    @Override
    public List<ParkingLotResponse> getAllParkingLots() {

        List<ParkingLotEntity> allParkingLots = repository.findAll();

        return allParkingLots.stream().map(responseMapper::toDTO).toList();
    }

    @Override
    public ParkingLotResponse getParkingLotById(UUID id) {

        ParkingLotEntity found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking Lot Not Found"));


        return responseMapper.toDTO(found);
    }

    @Override
    public ParkingLotResponse update(UUID id, ParkingLotRequest request) {

        ParkingLotEntity entity =
                repository.findByIdAndActiveTrue(id)
                        .orElseThrow(() -> new EntityNotFoundException("Parking Lot Not Found"));

        entity.setName(request.getName());
        entity.setAddress(addressRepository.
                findById(request.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found")));
        entity.setCapacity(request.getCapacity());


        ParkingLotEntity updatedParkingLotEntity = repository.save(entity);


        return responseMapper.toDTO(updatedParkingLotEntity);
    }


    @Override
    public void delete(UUID id) {

        ParkingLotEntity entity = repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking Lot Not Found"));

        entity.setActive(false);

        repository.save(entity);

    }

    @Override
    public void restore(UUID id) {

        ParkingLotEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking Lot Not Found"));

        entity.setActive(false);
        repository.save(entity);

    }

    @Override
    public List<ParkingLotResponse> getAllActiveParkingLots() {

        List<ParkingLotEntity> ActiveParkingLots = repository.findByActiveTrue();

        return ActiveParkingLots.stream().map(responseMapper::toDTO).toList();
    }

    public List<ParkingLotClientView> getAllParkingLotForClient() {

        ParkingLotClientView dto = new ParkingLotClientView();

        return repository.findAll()
                .stream()
                .map(this::toClientView)
                .toList();

    }



    private ParkingLotClientView toClientView(ParkingLotEntity parkingLot) {

        ParkingLotClientView view = new ParkingLotClientView();

        view.setName(parkingLot.getName());
        view.setCapacity(parkingLot.getCapacity());
        view.setStreet(parkingLot.getAddress().getStreet());
        view.setNumber(parkingLot.getAddress().getNumber());
        view.setZipCode(parkingLot.getAddress().getZipCode());

        return view;
    }


}
