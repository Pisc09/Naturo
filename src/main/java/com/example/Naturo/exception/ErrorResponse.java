package com.example.Naturo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse extends RuntimeException {

    private int status;
    private String message;
}
