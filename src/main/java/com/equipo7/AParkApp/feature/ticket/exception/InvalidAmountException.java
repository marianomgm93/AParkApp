package com.equipo7.AParkApp.feature.ticket.exception;

public class InvalidAmountException extends RuntimeException{

    public InvalidAmountException(String msg){
        super(msg);
    }
}
