package com.equipo7.AParkApp.common.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ExceptionDto {

    private String message;
    private LocalDateTime date;


}
