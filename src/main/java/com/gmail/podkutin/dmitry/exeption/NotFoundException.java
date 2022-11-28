package com.gmail.podkutin.dmitry.exeption;

public class NotFoundException extends RuntimeException {
    public  NotFoundException(String msg) {
        super(msg);
    }
}
