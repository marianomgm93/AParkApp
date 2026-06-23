package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.common.model.exceptions.EntityAlreadyExistsEx;
import com.equipo7.AParkApp.feature.auth.credentials.CredentialsEntity;
import com.equipo7.AParkApp.feature.auth.credentials.CredentialsRepository;
import com.equipo7.AParkApp.feature.auth.dto.NewAccountRequest;
import com.equipo7.AParkApp.feature.auth.permissions.RoleRepository;
import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import com.equipo7.AParkApp.feature.user.domain.mapper.UserRequestMapper;
import com.equipo7.AParkApp.feature.user.domain.mapper.UserResponseMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository ur;
    private final UserRequestMapper requestMapper;
    private final UserResponseMapper responseMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public UserResponse save(NewAccountRequest newAccountRequest) {

        if (ur.existsByEmail(newAccountRequest.email())) {
            throw new EntityAlreadyExistsEx("Email already Registered");
        }

        UserRequest newUser = new UserRequest(newAccountRequest.name(), newAccountRequest.email());

        UserEntity saved = ur.save(requestMapper.toEntity(newUser));

        CredentialsEntity newCredentials = CredentialsEntity.builder().roles(Set.of(roleRepository.findById(1L).orElseThrow()))
                .enabled(true)
                .username(newAccountRequest.email())
                .password(passwordEncoder.encode(newAccountRequest.password()))
                .usuario(saved)
                .build();

        credentialsRepository.save(newCredentials);

        return responseMapper.toDTO(saved);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return ur.findAll().stream()
                .map(responseMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponse getUserById(UUID userId) throws EntityNotFoundException {
        return ur.findById(userId).map(responseMapper::toDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse getUserByEmail(String userEmail) throws EntityNotFoundException {
        return ur.findByEmail(userEmail)
                .map(responseMapper::toDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse update(UUID id, UserRequest userRequest) {
        UserEntity user = findUserById(id);
        user.setName(userRequest.getName());
        UserEntity saved = ur.save(user);
        return responseMapper.toDTO(saved);
    }


    @Override
    public void delete(UUID userId) {
        responseMapper.toEntity(getUserById(userId)).setActive(false);
    }

    public UserEntity findUserById(UUID userId) {
        return ur.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
