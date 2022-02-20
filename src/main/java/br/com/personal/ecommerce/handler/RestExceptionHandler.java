package br.com.personal.ecommerce.handler;

import br.com.personal.ecommerce.dto.CustomExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

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
}
