package com.equipo7.AParkApp.feature.user.domain.mapper;

import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements IMapper<UserEntity, UserResponse> {
    UserResponse toDTO(UserEntity userEntity);
    UserEntity toEntity(UserResponse userResponse);
}
