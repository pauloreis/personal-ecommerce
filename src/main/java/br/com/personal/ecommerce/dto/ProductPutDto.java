package br.com.personal.ecommerce.dto;

import br.com.personal.ecommerce.domain.ProductCategory;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProductPutDto {
    private Long id;
    @NotEmpty(message = "Uuid cannot be empty")
    private UUID uuid;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Category cannot be empty")
    private ProductCategory category;
    @NotNull(message = "Prize cannot be null")
    private Double prize;
}
