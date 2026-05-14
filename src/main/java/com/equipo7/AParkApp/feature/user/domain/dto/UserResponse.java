package com.equipo7.AParkApp.feature.user.domain.dto;

import java.util.UUID;

public record UserResponse(UUID userId, String name, String email) {
}
