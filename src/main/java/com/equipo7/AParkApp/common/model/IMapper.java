package com.equipo7.AParkApp.common.model;

public interface IMapper <T,U> {
    T toEntity(U u);
    U toDTO(T t);
}
