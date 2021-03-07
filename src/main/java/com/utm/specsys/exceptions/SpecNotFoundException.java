package com.utm.specsys.exceptions;

public class SpecNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SpecNotFoundException(Long id) {
        super("Could not find spec" + id);
    }
}
