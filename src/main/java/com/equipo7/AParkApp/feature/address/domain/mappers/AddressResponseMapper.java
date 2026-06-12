package com.equipo7.AParkApp.feature.address.domain.mappers;

import com.equipo7.AParkApp.feature.address.AddressEntity;
import com.equipo7.AParkApp.feature.address.domain.dto.AddressRequest;
import com.equipo7.AParkApp.feature.address.domain.dto.AddressResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
public class AddressResponseMapper {

    private final ModelMapper modelMapper;

public AddressResponse toDTO(AddressEntity entity) {



   return modelMapper.map(entity,AddressResponse.class);
}


    public AddressEntity toEntity(AddressRequest dto) {


    return modelMapper.map(dto,AddressEntity.class);
    }

}
