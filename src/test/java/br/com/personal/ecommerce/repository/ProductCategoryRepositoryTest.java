package br.com.personal.ecommerce.repository;

import br.com.personal.ecommerce.domain.ProductCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for ProductCategory Repository")
class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    @DisplayName("Save ProductCategory when successful")
    void save_ProducCategory_WhenSuccessful(){
        ProductCategory productCategoryToBeSaved = createProductCategory();
        ProductCategory productCategorySaved = this.productCategoryRepository.save(productCategoryToBeSaved);

        Assertions.assertThat(productCategorySaved).isNotNull();
        Assertions.assertThat(productCategorySaved.getId()).isNotNull();
        Assertions.assertThat(productCategorySaved.getName()).isEqualTo(productCategoryToBeSaved.getName());
    }

    @Test
    @DisplayName("Update ProductCategory when successful")
    void update_ProducCategory_WhenSuccessful(){
        ProductCategory productCategoryToBeSaved = createProductCategory();
        ProductCategory productCategorySaved = this.productCategoryRepository.save(productCategoryToBeSaved);

        productCategorySaved.setName("Phone");
        ProductCategory productCategoryUpdated = this.productCategoryRepository.save(productCategorySaved);

        Assertions.assertThat(productCategoryUpdated).isNotNull();
        Assertions.assertThat(productCategoryUpdated.getId()).isNotNull();
        Assertions.assertThat(productCategoryUpdated.getName()).isEqualTo(productCategorySaved.getName());
    }

    @Test
    @DisplayName("Delete ProductCategory when successful")
    void delete_ProducCategory_WhenSuccessful(){
        ProductCategory productCategoryToBeSaved = createProductCategory();
        ProductCategory productCategorySaved = this.productCategoryRepository.save(productCategoryToBeSaved);

        this.productCategoryRepository.delete(productCategorySaved);
        Optional<ProductCategory> productCategoryOptional = this.productCategoryRepository.findById(productCategorySaved.getId());

        Assertions.assertThat(productCategoryOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name Return list of ProductCategory when successful")
    void findByName_ReturnListOfProducCategory_WhenSuccessful(){
        ProductCategory productCategoryToBeSaved = createProductCategory();
        ProductCategory productCategorySaved = this.productCategoryRepository.save(productCategoryToBeSaved);

        Optional<ProductCategory> productCategoryOptional = this.productCategoryRepository.findByName(productCategorySaved.getName());

        Assertions.assertThat(productCategoryOptional.isPresent());
        Assertions.assertThat(productCategoryOptional).contains(productCategorySaved);
    }

    @Test
    @DisplayName("Save Throw ConstraintViolationException When name is empty")
    void save_ThrowCConstraintViolationException_WhenNameIsEmpty(){
        ProductCategory productCategory = new ProductCategory();
//        Assertions.assertThatThrownBy( () -> this.productCategoryRepository.save(productCategory))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.productCategoryRepository.save(productCategory))
                .withMessageContaining("Name cannot empty");
    }

    private ProductCategory createProductCategory(){
        return ProductCategory.builder()
                .name("Eletronic")
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }
}