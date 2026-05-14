package com.equipo7.AParkApp.feature.user.domain.mapper;

import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;

@Mapper(componentModel = "spring")
public interface UserResponseMapper extends IMapper<UserEntity, UserResponse> {
    UserResponse toDTO(UserEntity userEntity);
    UserEntity toEntity(UserResponse userResponse);
}
