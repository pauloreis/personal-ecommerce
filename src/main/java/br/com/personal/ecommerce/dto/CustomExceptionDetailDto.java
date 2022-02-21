package br.com.personal.ecommerce.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CustomExceptionDetailDto extends CustomExceptionDto{
    private final String field;
    private final String fieldMessage;
}
