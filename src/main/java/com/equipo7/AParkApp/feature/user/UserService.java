package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import com.equipo7.AParkApp.feature.user.domain.mapper.UserRequestMapper;
import com.equipo7.AParkApp.feature.user.domain.mapper.UserResponseMapper;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class UserService implements IUserService {
    private UserRepository ur;
    private UserRequestMapper requestMapper;
    private UserResponseMapper responseMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return ur.findAll().stream()
                .map(responseMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return ur.findById(userId).map(responseMapper::toDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse getUserByEmail(String userEmail) {
        return ur.findByEmail(userEmail)
                .map(responseMapper::toDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        return ur.findByEmail(userRequest.email())
                .map(userEntity -> userEntity.setName(userRequest.getName()))
                .map(responseMapper::toDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        UserEntity toBeSave=requestMapper.toEntity(userRequest);
        UserEntity saved= ur.save(toBeSave);
        return responseMapper.toDTO(saved);
    }

    @Override
    public void delete(UUID userId) {

    }
}
