package com.utm.specsys.exceptions;

public class FileNotFoundForSpecException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FileNotFoundForSpecException(String name, Long specId){
        super("Could not find file " + name + " for spec " + specId);
    }
}
