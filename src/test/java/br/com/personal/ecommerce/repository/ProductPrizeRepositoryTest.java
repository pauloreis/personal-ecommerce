package br.com.personal.ecommerce.repository;

import br.com.personal.ecommerce.domain.ProductPrize;
import br.com.personal.ecommerce.util.ProductPrizeCreator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for ProductPrize Repository")
class ProductPrizeRepositoryTest {

    @Autowired
    ProductPrizeRepository productPrizeRepository;

    @Test
    @DisplayName("Save ProductPrize when successful")
    void save_ProductPrize_WhenSuccessful(){
        ProductPrize productPrizeToBeSaved = ProductPrizeCreator.createProductPrizeToBeSave();
        ProductPrize productPrizeSaved = this.productPrizeRepository.save(productPrizeToBeSaved);

        Assertions.assertThat(productPrizeSaved).isNotNull();
        Assertions.assertThat(productPrizeSaved.getId()).isNotNull();
        Assertions.assertThat(productPrizeSaved.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Find By Id Return ProductPrize when Successful")
    void findById_ReturnProductPrize_WhenSuccessful(){
        ProductPrize productPrizeToBeSaved = ProductPrizeCreator.createProductPrizeToBeSave();
        ProductPrize productPrizeSaved = this.productPrizeRepository.save(productPrizeToBeSaved);

        Optional<ProductPrize> productPrize = this.productPrizeRepository.findById(productPrizeSaved.getId());

        Assertions.assertThat(productPrize).isPresent();
        Assertions.assertThat(productPrize).contains(productPrizeSaved);
    }

    @Test
    @DisplayName("Delete Set End Date When successful")
    void delete_SetEndDate_WhenSuccessful(){
        ProductPrize productPrizeToBeSaved = ProductPrizeCreator.createProductPrizeInvalid();
        ProductPrize productPrizeSaved = this.productPrizeRepository.save(productPrizeToBeSaved);

        Optional<ProductPrize> productPrize = this.productPrizeRepository.findById(productPrizeSaved.getId());

        Assertions.assertThat(productPrize.get().getEndDate()).isNotNull();
    }
}