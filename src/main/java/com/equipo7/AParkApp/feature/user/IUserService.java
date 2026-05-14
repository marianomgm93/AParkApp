package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(UUID userId);
    UserResponse getUserByEmail(String userEmail);
    UserResponse update(UserRequest userRequest);
    UserResponse save(UserRequest userRequest);

    void delete(UUID userId);
}
