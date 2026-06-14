package com.equipo7.AParkApp.feature.auth.dto;

public record NewAccountRequest(String name, String password, String email) {
}