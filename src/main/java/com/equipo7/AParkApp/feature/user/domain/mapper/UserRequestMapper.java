package com.equipo7.AParkApp.feature.user.domain.mapper;
import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestMapper implements IMapper<UserEntity, UserRequest>{
    private final ModelMapper mapper;
    public UserRequest toDTO(UserEntity userEntity) {
        return mapper.map(userEntity, UserRequest.class);
    }

    public UserEntity toEntity(UserRequest userRequest) {
        return mapper.map(userRequest, UserEntity.class);
    }
}
