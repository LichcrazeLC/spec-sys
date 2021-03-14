package com.utm.specsys.exceptions;

public class PropertyNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PropertyNotFoundException(Long id){
        super("Could not find property" + id);
    }
}
