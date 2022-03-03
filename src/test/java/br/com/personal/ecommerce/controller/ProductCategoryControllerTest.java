package br.com.personal.ecommerce.controller;

import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.service.ProductCategoryService;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ProductCategoryControllerTest {
    @InjectMocks // class be test
    private ProductCategoryController productCategoryController;

    @Mock // class inner used inner controller
    private ProductCategoryService productCategoryServiceMock;

    @BeforeEach // before the methods tests
    void setUp(){
        //create object with objects to returned
        List<ProductCategory> productCategoryList = List.of(ProductCategoryCreator.createProductCategoryValid());
        //create mock when service have be called
        BDDMockito.when(productCategoryServiceMock.listAll()).thenReturn(productCategoryList);
        BDDMockito.when(productCategoryServiceMock.findById(ArgumentMatchers.any()))
                .thenReturn(ProductCategoryCreator.createProductCategoryValid());
        BDDMockito.when(productCategoryServiceMock.findByName(ArgumentMatchers.any()))
                .thenReturn(ProductCategoryCreator.createProductCategoryValid());
        BDDMockito.when(productCategoryServiceMock.save(ArgumentMatchers.any(ProductCategory.class)))
                .thenReturn(ProductCategoryCreator.createProductCategoryValid());
    }

    @Test
    @DisplayName("List All Return List Of Product Category When Successful")
    void listAll_ReturnListOfProductCategory_WhenSuccessful(){
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();
        List<ProductCategory> productCategoryList = productCategoryController.list().getBody();
        Assertions.assertThat(productCategoryList).isNotNull();
        Assertions.assertThat(productCategoryList).isNotEmpty();
        Assertions.assertThat(productCategoryList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById Return Product Category When Successful")
    void findById_ReturnProductCategory_WhenSuccessful(){
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();
        ProductCategory productCategory = productCategoryController.findById(1L).getBody();
        Assertions.assertThat(productCategory).isNotNull();
        Assertions.assertThat(productCategory.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName Return Product Category When Successful")
    void findByName_ReturnProductCategory_WhenSuccessful(){
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();
        ProductCategory productCategory = productCategoryController.findByName(expectedName).getBody();
        Assertions.assertThat(productCategory).isNotNull();
        Assertions.assertThat(productCategory.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save Return Product Category When Successful")
    void save_ReturnProductCategory_WhenSuccessful(){
        ProductCategory productCategoryTobeSaved = ProductCategoryCreator.createProductCategoryTobeSaved();
        ProductCategory productCategory = productCategoryController.save(productCategoryTobeSaved).getBody();
        Assertions.assertThat(productCategory).isNotNull();
        Assertions.assertThat(productCategory.getName()).isEqualTo(productCategoryTobeSaved.getName());
    }

    //Not Successful cases
    @Test
    @DisplayName("findByName Return Null When Product Category is not found")
    void findByName_ReturnNull_WhenProductCategoryIsNotFound(){
        //this declaration preceded the beforeeach
        BDDMockito.when(productCategoryServiceMock.findByName(ArgumentMatchers.any()))
                .thenReturn(null);
        String expectedName = ProductCategoryCreator.createProductCategoryValid().getName();
        ProductCategory productCategory = productCategoryController.findByName(expectedName).getBody();
        Assertions.assertThat(productCategory).isNull();
    }
}