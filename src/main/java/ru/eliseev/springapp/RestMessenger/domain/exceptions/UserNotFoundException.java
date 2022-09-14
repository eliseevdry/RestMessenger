package ru.eliseev.springapp.RestMessenger.domain.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(long id) {
        super("User with id " + id + " not found");
    }
}
