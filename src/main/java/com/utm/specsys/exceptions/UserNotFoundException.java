package com.utm.specsys.exceptions;

public class UserNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String id) {
        super("Could not find user " + id);
    }
}
