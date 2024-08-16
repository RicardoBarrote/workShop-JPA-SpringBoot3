package project.workshop.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.workshop.services.exceptions.PropertyNull;
import project.workshop.services.exceptions.ResourcerNotFound;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourcerNotFound.class)
    public ResponseEntity<StandardError> NoSuchElementException(ResourcerNotFound e, HttpServletRequest request) {
        String error = "Check if the identifier is correct";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(PropertyNull.class)
    public ResponseEntity<StandardError> PropertyValueException(PropertyNull e, HttpServletRequest request) {
        String error = "Check that all fields have been filled in";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError standardError = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}