package com.equipo7.AParkApp.feature.user.domain.mapper;
import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper implements IMapper<UserEntity, UserRequest>{
    UserRequest toDTO(UserEntity userEntity);
    UserEntity toEntity(UserRequest userRequest);
}
