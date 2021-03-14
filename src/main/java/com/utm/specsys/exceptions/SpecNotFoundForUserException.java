package com.utm.specsys.exceptions;

public class SpecNotFoundForUserException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SpecNotFoundForUserException(Long id, Long userId){
        super("Could not find spec " + id + " for user " + userId);
    }
}
