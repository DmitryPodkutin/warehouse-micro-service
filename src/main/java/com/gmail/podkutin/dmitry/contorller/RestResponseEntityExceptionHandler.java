package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.exeption.ErrorInfo;
import com.gmail.podkutin.dmitry.exeption.ErrorType;
import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleFoundException(NotFoundException exception) {
        return ResponseEntity.status(ErrorType.NOT_FOUND.getStatus()).body(exception.getMessage());
    }

    @ExceptionHandler(EquipmentException.class)
    public ResponseEntity<String> handleEquipmentException(EquipmentException exception) {
        return ResponseEntity.status(ErrorType.APP_ERROR.getStatus()).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorInfo> onConstraintValidationException(HttpServletRequest req,
                                                                     ConstraintViolationException e) {
        return ResponseEntity.status(ErrorType.VALIDATION_ERROR.getStatus())
                .body(new ErrorInfo(req.getRequestURL(), ErrorType.VALIDATION_ERROR,
                        e.getMessage()));
    }
}
