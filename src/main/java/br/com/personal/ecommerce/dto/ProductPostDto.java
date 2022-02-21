package br.com.personal.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProductPostDto {
    private UUID uuid = UUID.randomUUID();
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Category cannot be empty")
    private String category;
    @NotNull(message = "Prize cannot be null")
    private Double prize;
}
