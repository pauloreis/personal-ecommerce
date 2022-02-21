package br.com.personal.ecommerce.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CustomExceptionDto {
    protected Integer statusCode;
    protected String title;
    protected String message;
}
