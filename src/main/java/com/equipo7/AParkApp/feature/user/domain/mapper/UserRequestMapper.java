package com.equipo7.AParkApp.feature.user.domain.mapper;
import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;

@Mapper(componentModel = "spring")
public interface UserRequestMapper extends IMapper<UserEntity, UserRequest>{
    UserRequest toDTO(UserEntity userEntity);
    UserEntity toEntity(UserRequest userRequest);
}
