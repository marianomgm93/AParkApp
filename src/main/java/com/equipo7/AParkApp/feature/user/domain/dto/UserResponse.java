package com.equipo7.AParkApp.feature.user.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    String name;
    String email;
}
