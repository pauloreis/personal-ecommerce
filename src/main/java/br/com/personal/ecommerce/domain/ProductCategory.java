package br.com.personal.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductCategory {
    private Long id;
    private UUID uuid;
    private String name;
}
