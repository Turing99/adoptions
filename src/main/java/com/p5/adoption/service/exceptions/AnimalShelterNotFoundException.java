package com.p5.adoption.service.exceptions;

public class AnimalShelterNotFoundException extends RuntimeException {

    public AnimalShelterNotFoundException(String message) {
        super(message);
    }
}
