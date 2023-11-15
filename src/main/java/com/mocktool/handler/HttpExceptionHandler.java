package com.mocktool.handler;

import com.mocktool.dto.MessageDTO;
import com.mocktool.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class HttpExceptionHandler {


    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody MessageDTO handleNotFoundError(NotFoundException notFoundException) {
        return new MessageDTO(notFoundException.getMessage(), NOT_FOUND.value());
    }
}
