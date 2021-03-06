package com.utm.specsys.controllers;

import java.time.LocalDateTime;

import com.utm.specsys.exceptions.CustomErrorResponse;
import com.utm.specsys.exceptions.FileNotFoundForSpecException;
import com.utm.specsys.exceptions.SpecNotFoundException;
import com.utm.specsys.exceptions.SpecNotFoundForUserException;
import com.utm.specsys.exceptions.UserCreationFailedException;
import com.utm.specsys.exceptions.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, javax.ws.rs.NotFoundException.class, SpecNotFoundException.class, SpecNotFoundForUserException.class, FileNotFoundForSpecException.class})
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({UserCreationFailedException.class})
    public ResponseEntity<CustomErrorResponse> customHandleConflict(Exception ex) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({org.keycloak.authorization.client.util.HttpResponseException.class})
    public ResponseEntity<CustomErrorResponse> customHandleUnauthorized(Exception ex) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

}