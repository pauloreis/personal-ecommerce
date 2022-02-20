package br.com.personal.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomExceptionDto {
    private Integer statusCode;
    private String title;
    private String message;
}
