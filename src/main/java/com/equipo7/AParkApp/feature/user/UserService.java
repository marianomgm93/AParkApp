package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import com.equipo7.AParkApp.feature.user.domain.mapper.UserRequestMapper;
import com.equipo7.AParkApp.feature.user.domain.mapper.UserResponseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository ur;
    private final UserRequestMapper requestMapper;
    private final UserResponseMapper responseMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return ur.findAll().stream()
                .map(responseMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponse getUserById(UUID userId) throws  EntityNotFoundException{
        return ur.findById(userId).map(responseMapper::toDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse getUserByEmail(String userEmail) throws EntityNotFoundException{
        return ur.findByEmail(userEmail)
                .map(responseMapper::toDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse update(UserRequest userRequest){
        UserEntity user= responseMapper.toEntity(getUserByEmail(userRequest.getEmail()));
        user.setName(userRequest.getName());
        UserEntity saved=ur.save(user);
        return responseMapper.toDTO(saved);
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        UserEntity toBeSave=requestMapper.toEntity(userRequest);
        UserEntity saved= ur.save(toBeSave);
        return responseMapper.toDTO(saved);
    }

    @Override
    public void delete(UUID userId) {
        responseMapper.toEntity(getUserById(userId)).setActive(false);
    }
}
