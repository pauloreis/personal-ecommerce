package br.com.personal.ecommerce.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductPostDto {
    private UUID uuid = UUID.randomUUID();
    private String name;
    private String category;
    private Double prize;
}
