package ru.rebs.bonus.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDto handleIllegalStateException(HttpServletRequest request, Exception exception) {
        log.warn("Request '{}' was interrupted: '{}'", request.getRequestURI(), exception.getMessage());
        return new ExceptionDto(exception.getMessage());
    }

}
