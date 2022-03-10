package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.ProductPrize;
import br.com.personal.ecommerce.repository.ProductPrizeRepository;
import br.com.personal.ecommerce.service.impl.ProductPrizeServiceImpl;
import br.com.personal.ecommerce.util.ProductPrizeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProductPrizeServiceTest {

    @InjectMocks
    private ProductPrizeServiceImpl productPrizeService;

    @Mock
    private ProductPrizeRepository productPrizeRepositoryMock;

    @BeforeEach
    void setUp(){
        BDDMockito.when(productPrizeRepositoryMock.save(ArgumentMatchers.any(ProductPrize.class)))
                        .thenReturn(ProductPrizeCreator.createProductPrizeValid());

        BDDMockito.when(productPrizeRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(ProductPrizeCreator.createProductPrizeValid()));
    }

    @Test
    @DisplayName("save Return Product Prize When Successful")
    void save_ReturnProductPrize_WhenSuccessful() {
        ProductPrize productPrizeToBeSave = ProductPrizeCreator.createProductPrizeToBeSave();
        ProductPrize productPrizeSaved = productPrizeService.save(productPrizeToBeSave);

        Assertions.assertThat(productPrizeSaved).isNotNull();
        Assertions.assertThat(productPrizeSaved.getPrize()).isEqualTo(productPrizeToBeSave.getPrize());
    }

    @Test
    @DisplayName("findById Return Product Prize When Successful")
    void findById_ReturnProductPrize_WhenSuccessful() {
        Double expectedPrize = ProductPrizeCreator.createProductPrizeValid().getPrize();
        ProductPrize productPrize = productPrizeService.findById(1L);

        Assertions.assertThat(productPrize).isNotNull();
        Assertions.assertThat(productPrize.getPrize()).isEqualTo(expectedPrize);
    }

    @Test
    @DisplayName("replace update product prize end date when successful")
    void replace_updateProductPrizeEndDate_WhenSuccessful() {
        ProductPrize productPrizeTobeDeleted = ProductPrizeCreator.createProductPrizeInvalid();
        Assertions.assertThatCode(
                () -> productPrizeService.replace(productPrizeTobeDeleted.getId()))
                .doesNotThrowAnyException();
    }
}