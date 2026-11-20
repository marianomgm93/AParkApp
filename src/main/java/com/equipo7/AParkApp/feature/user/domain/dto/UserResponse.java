package com.equipo7.AParkApp.feature.user.domain.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    UUID id;
    String name;
    String email;
    List<String> roles;
    boolean active;
}
