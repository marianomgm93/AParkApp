package com.equipo7.AParkApp.feature.user.domain.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public record UserResponse(UUID userId, String name, String email) {
}
