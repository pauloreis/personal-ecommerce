package br.com.personal.ecommerce.repository;

import br.com.personal.ecommerce.domain.ProductCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
@DisplayName("Tests for ProductCategory Repository")
class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    @DisplayName("Save ProductCategory when successful")
    void save_PersistProoucCategory_WhenSuccessful(){
        ProductCategory productCategoryToBeSaved = createProductCategory();
        ProductCategory productCategorySaved = this.productCategoryRepository.save(productCategoryToBeSaved);

        Assertions.assertThat(productCategorySaved).isNotNull();
        Assertions.assertThat(productCategorySaved.getId()).isNotNull();
        Assertions.assertThat(productCategorySaved.getName()).isEqualTo(productCategoryToBeSaved.getName());
    }

    private ProductCategory createProductCategory(){
        return ProductCategory.builder()
                .name("Eletronic")
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }
}