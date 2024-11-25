package com.week2.SpringRestCrud.Exceptions;

import java.io.IOException;

public class IntegerFormatException extends IOException {
    public IntegerFormatException(String message) {
        super(message);
    }
}
