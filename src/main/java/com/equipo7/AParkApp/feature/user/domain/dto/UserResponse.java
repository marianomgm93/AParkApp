package com.equipo7.AParkApp.feature.user.domain.dto;

import lombok.*;

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
}
