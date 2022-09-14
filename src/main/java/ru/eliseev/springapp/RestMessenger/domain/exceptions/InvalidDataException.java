package ru.eliseev.springapp.RestMessenger.domain.exceptions;

public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
