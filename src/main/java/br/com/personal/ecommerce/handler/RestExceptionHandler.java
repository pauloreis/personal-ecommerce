package br.com.personal.ecommerce.handler;

import br.com.personal.ecommerce.dto.CustomExceptionDetailDto;
import br.com.personal.ecommerce.dto.CustomExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomExceptionDto> handlerResponseStatusException(ResponseStatusException exception){
        return new ResponseEntity(
                CustomExceptionDto.builder()
                        .statusCode(exception.getStatus().value())
                        .title(exception.getReason())
                        .message(exception.getMessage())
                        .build(),
                exception.getStatus()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomExceptionDetailDto> handlerRMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldError> fieldsErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldsErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String messages = fieldsErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                CustomExceptionDetailDto.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception. Invalid Fields.")
                        .message("Invalid Fields.")
                        .field(fields)
                        .fieldMessage(messages)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
