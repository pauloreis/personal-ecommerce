package br.com.personal.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductPostDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Category cannot be empty")
    private String category;
    @NotNull(message = "Prize cannot be null")
    private Long prizeId;
}
