package br.com.personal.ecommerce.domain;

import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private ProductCategory category;
    private Double prize;

    public static Product converter(ProductPostDto productPostDto){
        return Product.builder()
                .uuid(productPostDto.getUuid())
                .name(productPostDto.getName())
                .category(productPostDto.getCategory())
                .prize(productPostDto.getPrize())
                .build();
    }

    public static Product converter(ProductPutDto productPutDto, Long id){
        return Product.builder()
                .id(id)
                .uuid(productPutDto.getUuid())
                .name(productPutDto.getName())
                .category(productPutDto.getCategory())
                .prize(productPutDto.getPrize())
                .build();
    }
}
