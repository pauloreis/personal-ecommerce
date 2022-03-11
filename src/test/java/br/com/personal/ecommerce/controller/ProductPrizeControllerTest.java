package br.com.personal.ecommerce.controller;

import br.com.personal.ecommerce.domain.ProductPrize;
import br.com.personal.ecommerce.service.ProductPrizeService;
import br.com.personal.ecommerce.util.ProductCategoryCreator;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ProductPrizeControllerTest {

    @InjectMocks
    private ProductPrizeController productPrizeController;

    @Mock
    private ProductPrizeService productPrizeServiceMock;

    @BeforeEach
    void setUp(){
        BDDMockito.when(productPrizeServiceMock.findById(ArgumentMatchers.any()))
                .thenReturn(ProductPrizeCreator.createProductPrizeValid());
        BDDMockito.when(productPrizeServiceMock.save(ArgumentMatchers.any(ProductPrize.class)))
                        .thenReturn(ProductPrizeCreator.createProductPrizeValid());
        BDDMockito.doNothing().when(productPrizeServiceMock).replace(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("save Return Product Prize When Successful")
    void save_ReturnProductPrize_WhenSuccessful() {
        ProductPrize productPrizeToBeSave = ProductPrizeCreator.createProductPrizeToBeSave();
        ProductPrize productPrize = productPrizeController.save(productPrizeToBeSave).getBody();

        Assertions.assertThat(productPrize).isNotNull();
        Assertions.assertThat(productPrize.getPrize()).isEqualTo(productPrizeToBeSave.getPrize());
    }

    @Test
    @DisplayName("findById Return ProductPrize When Successful")
    void findById_ReturnProductPrize_WhenSuccessful() {
        ProductPrize productPrize = ProductPrizeCreator.createProductPrizeValid();
        ProductPrize productPrizeFound = productPrizeController.findById(productPrize.getId()).getBody();

        Assertions.assertThat(productPrizeFound).isNotNull();
        Assertions.assertThat(productPrizeFound.getPrize()).isEqualTo(productPrize.getPrize());
    }

    @Test
    @DisplayName("delete Update Product Prize end date When successfull")
    void delete_UpdateProductPrizeEndDate_WhenSuccessful() {
        ProductPrize productPrizeToBeDeleted = ProductPrizeCreator.createProductPrizeInvalid();
        Assertions.assertThatCode(
                () -> productPrizeController.delete(productPrizeToBeDeleted.getId()))
                .doesNotThrowAnyException();
    }
}