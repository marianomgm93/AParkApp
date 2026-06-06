package com.equipo7.AParkApp.feature.address.domain.mappers;

import com.equipo7.AParkApp.feature.address.AddressEntity;
import com.equipo7.AParkApp.feature.address.domain.dto.AddressRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter
@Getter
public class AddressRequestMapper {
    private final ModelMapper modelMapper;

    public AddressRequest toDTO(AddressEntity entity) {


        return modelMapper.map(entity, AddressRequest.class);
    }


    public AddressEntity toEntity(AddressRequest dto) {


        return modelMapper.map(dto, AddressEntity.class);
    }


}
