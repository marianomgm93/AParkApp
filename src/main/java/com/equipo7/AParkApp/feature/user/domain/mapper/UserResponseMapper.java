package com.equipo7.AParkApp.feature.user.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResponseMapper implements IMapper<UserEntity, UserResponse> {
    private final ModelMapper mapper;
    public UserResponse toDTO(UserEntity userEntity) {
        return mapper.map(userEntity, UserResponse.class);
    }

    public UserEntity toEntity(UserResponse userResponse){
        return mapper.map(userResponse, UserEntity.class);
    }
}
