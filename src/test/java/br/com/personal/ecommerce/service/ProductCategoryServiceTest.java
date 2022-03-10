package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.repository.ProductCategoryRepository;
import br.com.personal.ecommerce.service.impl.ProductCategoryServiceImpl;
import br.com.personal.ecommerce.util.ProductCategoryCreator;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProductCategoryServiceTest {
    @InjectMocks // class be test
    private ProductCategoryServiceImpl productCategoryService;

    @Mock // class inner used inner controller
    private ProductCategoryRepository productCategoryRepositoryMock;

    @BeforeEach
        // before the methods tests
    void setUp(){
        //create object with objects to returned
        List<ProductCategory> productCategoryList = List.of(ProductCategoryCreator.createProductCategoryValid());
        //create mock when service have be called
        BDDMockito.when(productCategoryRepositoryMock.findAll()).thenReturn(productCategoryList);
        BDDMockito.when(productCategoryRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(ProductCategoryCreator.createProductCategoryValid()));
        BDDMockito.when(productCategoryRepositoryMock.findByName(ArgumentMatchers.any()))
                .thenReturn(Optional.of(ProductCategoryCreator.createProductCategoryValid()));
        BDDMockito.when(productCategoryRepositoryMock.save(ArgumentMatchers.any(ProductCategory.class)))
                .thenReturn(ProductCategoryCreator.createProductCategoryValid());
        BDDMockito.doNothing().when(productCategoryRepositoryMock).delete(ArgumentMatchers.any(ProductCategory.class));
    }

    @Test
    @DisplayName("List All Return List Of Product Category When Successful")
    void listAll_ReturnListOfProductCategory_WhenSuccessful(){
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();
        List<ProductCategory> productCategoryList = productCategoryService.listAll();
        Assertions.assertThat(productCategoryList).isNotNull();
        Assertions.assertThat(productCategoryList).isNotEmpty();
        Assertions.assertThat(productCategoryList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById Return Product Category When Successful")
    void findById_ReturnProductCategory_WhenSuccessful(){
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();
        ProductCategory productCategory = productCategoryService.findById(1L);
        Assertions.assertThat(productCategory).isNotNull();
        Assertions.assertThat(productCategory.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName Return Product Category When Successful")
    void findByName_ReturnProductCategory_WhenSuccessful(){
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();
        ProductCategory productCategory = productCategoryService.findByName(expectedName);
        Assertions.assertThat(productCategory).isNotNull();
        Assertions.assertThat(productCategory.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save Return Product Category When Successful")
    void save_ReturnProductCategory_WhenSuccessful(){
        ProductCategory productCategoryTobeSaved = ProductCategoryCreator.createProductCategoryTobeSaved();
        ProductCategory productCategory = productCategoryService.save(productCategoryTobeSaved);
        Assertions.assertThat(productCategory).isNotNull();
        Assertions.assertThat(productCategory.getName()).isEqualTo(productCategoryTobeSaved.getName());
    }

    @Test
    @DisplayName("replace update Product Category When Successful")
    void replace_UpdateProductCategory_WhenSuccessful(){
        Assertions.assertThatCode( () ->
                        productCategoryService.replace(ProductCategoryCreator.createProductCategoryUpdated()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete Remove Product Category When Successful")
    void delete_RemoveProductCategory_WhenSuccessful(){
        Assertions.assertThatCode( () ->
                        productCategoryService.delete(1L))
                .doesNotThrowAnyException();
    }

    //Not Successful cases
    @Test
    @DisplayName("findByName Return Null When Product Category is not found")
    void findByName_ReturnNull_WhenProductCategoryIsNotFound(){
        //this declaration preceded the beforeeach
        BDDMockito.when(productCategoryRepositoryMock.findByName(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> productCategoryService.findByName(expectedName))
                .withMessageContaining("ProductCategory not found.");
    }
}