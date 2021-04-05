package com.utm.specsys.exceptions;

public class UserCreationFailedException extends RuntimeException{
        /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserCreationFailedException() {
        super("Failed to register user. This email address is already in use.");
    }
}
