package br.com.personal.ecommerce.integration;

import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.repository.ProductCategoryRepository;
import br.com.personal.ecommerce.util.ProductCategoryCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ProductCategoryIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    @DisplayName("List All Return List Of Product Category When Successful")
    void listAll_ReturnListOfProductCategory_WhenSuccessful(){
        ProductCategory savedProductCategory = productCategoryRepository.save(ProductCategoryCreator.createProductCategoryValid());
        String expectedName = savedProductCategory.getName();

        ProductCategory[] productCategoryList = testRestTemplate.getForObject("/products/categorys", ProductCategory[].class);
        Assertions.assertThat(productCategoryList).isNotNull();
        Assertions.assertThat(productCategoryList).isNotEmpty();
        Assertions.assertThat(productCategoryList[0].getName()).isEqualTo(expectedName);
    }
}
