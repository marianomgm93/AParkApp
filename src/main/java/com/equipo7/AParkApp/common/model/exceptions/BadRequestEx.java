package com.equipo7.AParkApp.common.model.exceptions;

public class BadRequestEx extends RuntimeException {
    public BadRequestEx(String message) {
        super(message);
    }
}
