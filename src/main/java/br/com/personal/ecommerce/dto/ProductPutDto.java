package br.com.personal.ecommerce.dto;

import br.com.personal.ecommerce.domain.ProductCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductPutDto {
    private Long id;
    private UUID uuid;
    private String name;
    private ProductCategory category;
    private Double prize;
}
