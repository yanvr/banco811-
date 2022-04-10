package com.santander.banco811.controller.handler;

import com.santander.banco811.exception.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionResponse> beanValidationHandler(MethodArgumentNotValidException exception) {
        List<ExceptionResponse> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    fieldError.getField(),
                    mensagem,
                    LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
            );
            erros.add(exceptionResponse);
        });
        return erros;
    }
}
