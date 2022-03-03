package br.com.personal.ecommerce.util;

import br.com.personal.ecommerce.domain.ProductCategory;

import java.time.LocalDateTime;

public class ProductCategoryCreator {

    public static ProductCategory createProductCategoryTobeSaved(){
        return ProductCategory.builder()
                .name("Eletronic")
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public static ProductCategory createProductCategoryValid(){
        return ProductCategory.builder()
                .id(1L)
                .name("Eletronic")
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public static ProductCategory createProductCategoryUpdated(){
        return ProductCategory.builder()
                .id(1L)
                .name("Eletronic 2")
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }

}
