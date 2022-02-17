package br.com.personal.ecommerce.dto;

import br.com.personal.ecommerce.domain.ProductCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductPostDto {
    private UUID uuid = UUID.randomUUID();
    private String name;
    private ProductCategory category;
    private Double prize;
}
