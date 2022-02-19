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
    private UUID uuid = UUID.randomUUID();
    private String name;

    @ManyToOne
    private ProductCategory category;

    private Double prize;
    private LocalDateTime creatAt = LocalDateTime.now();
    private LocalDateTime updateAt = LocalDateTime.now();

    public static Product converter(ProductPostDto productPostDto){
        return Product.builder()
                .uuid(productPostDto.getUuid())
                .name(productPostDto.getName())
                .prize(productPostDto.getPrize())
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public static Product converter(ProductPutDto productPutDto, Long id){
        return Product.builder()
                .id(id)
                .uuid(productPutDto.getUuid())
                .name(productPutDto.getName())
                .category(productPutDto.getCategory())
                .prize(productPutDto.getPrize())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
