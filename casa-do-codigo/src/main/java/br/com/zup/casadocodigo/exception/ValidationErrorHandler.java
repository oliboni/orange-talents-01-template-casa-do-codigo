package br.com.zup.casadocodigo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler{
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationErrorsOutputDto> handler (MethodArgumentNotValidException exception){
        List<ValidationErrorsOutputDto> list = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationErrorsOutputDto error = new ValidationErrorsOutputDto(e.getField(),message);
            list.add(error);
        });

        return list;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    public ValidationErrorsOutputDto notFound (ResponseStatusException exception){
        String message = exception.getMessage();
        ValidationErrorsOutputDto errorsOutputDto = new ValidationErrorsOutputDto(null, message);

        return errorsOutputDto;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ValidationErrorsOutputDto illegalState (IllegalStateException exception){
        String message = exception.getMessage();
        ValidationErrorsOutputDto errorsOutputDto = new ValidationErrorsOutputDto(null, message);

        return errorsOutputDto;
    }
}
