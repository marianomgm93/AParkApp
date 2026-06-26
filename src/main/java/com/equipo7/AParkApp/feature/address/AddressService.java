package com.equipo7.AParkApp.feature.address;

import com.equipo7.AParkApp.common.model.exceptions.EntityAlreadyExistsEx;
import com.equipo7.AParkApp.feature.address.domain.dto.AddressRequest;
import com.equipo7.AParkApp.feature.address.domain.dto.AddressResponse;
import com.equipo7.AParkApp.feature.address.domain.mappers.AddressRequestMapper;
import com.equipo7.AParkApp.feature.address.domain.mappers.AddressResponseMapper;
import com.equipo7.AParkApp.feature.parkingLot.exception.AddressNotFound;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressResponseMapper responseMapper;
    private final AddressRequestMapper requestMapper;

    public AddressResponse crearDireccion(AddressRequest request) {

        AddressEntity entity = requestMapper.toEntity(request);
        AddressEntity saved = addressRepository.save(entity);


        return responseMapper.toDTO(saved);

    }

    public AddressResponse ObtenerDireccion(UUID id) {
        AddressEntity direccion = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        return responseMapper.toDTO(direccion);
    }

    public List<AddressResponse> obtenerTodasLasDirecciones() {

        return addressRepository.findAll().stream().map(responseMapper::toDTO).toList();
    }

    public void eliminarDireccion(UUID id) {

        AddressEntity entity = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        addressRepository.delete(entity);


    }

    public AddressResponse ActualizarDireccion(UUID id, AddressRequest request) {

        AddressEntity entity = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        AddressEntity nuevo = requestMapper.toEntity(request);

        entity.setStreet(nuevo.getStreet());
        entity.setNumber(nuevo.getNumber());
        entity.setZipCode(nuevo.getZipCode());
        entity.setNotes(nuevo.getNotes());

        AddressEntity saved = addressRepository.save(entity);

        return responseMapper.toDTO(saved);

    }


}
