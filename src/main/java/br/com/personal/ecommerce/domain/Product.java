package br.com.personal.ecommerce.domain;

import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "products")
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private ProductCategory category;

    @ManyToOne
    private ProductPrize productPrize;

    @Column(nullable = false)
    private LocalDateTime creatAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public static Product converter(ProductPostDto productPostDto, ProductCategory category, ProductPrize prize){
        return Product.builder()
                .uuid(UUID.randomUUID())
                .name(productPostDto.getName())
                .category(category)
                .productPrize(prize)
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public static Product converter(ProductPutDto productPutDto, Product product){
        return Product.builder()
                .id(product.getId())
                .uuid(UUID.fromString(productPutDto.getUuid()))
                .name(productPutDto.getName())
                .category(product.getCategory())
                .productPrize(product.getProductPrize())
                .creatAt(product.getCreatAt())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
