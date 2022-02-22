package br.com.personal.ecommerce.handler;

import br.com.personal.ecommerce.dto.CustomExceptionDetailDto;
import br.com.personal.ecommerce.dto.CustomExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomExceptionDto> handleResponseStatusException(ResponseStatusException exception){
        return new ResponseEntity(
                CustomExceptionDto.builder()
                        .statusCode(exception.getStatus().value())
                        .title(exception.getReason())
                        .message(exception.getMessage())
                        .build(),
                exception.getStatus()
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldsErrors = ex.getBindingResult().getFieldErrors();
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

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomExceptionDto customExceptionDto = CustomExceptionDto.builder()
                .statusCode(status.value())
                .title(ex.getCause().getMessage())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity(customExceptionDto, headers, status);
    }
}
