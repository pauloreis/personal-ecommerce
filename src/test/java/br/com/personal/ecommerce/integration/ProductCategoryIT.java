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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
//anotation for drop h2 database before each method.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

        List<ProductCategory> productCategoryList = testRestTemplate.exchange("/products/categorys", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductCategory>>(){
                }).getBody();

        Assertions.assertThat(productCategoryList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(productCategoryList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById Return Product Category When Successful")
    void findById_ReturnProductCategory_WhenSuccessful(){
        ProductCategory savedProductCategory = productCategoryRepository.save(ProductCategoryCreator.createProductCategoryValid());
        Long expectedId = savedProductCategory.getId();

        ProductCategory productCategory = testRestTemplate.getForObject("/products/categorys/{id}", ProductCategory.class, expectedId);

        Assertions.assertThat(productCategory).isNotNull();
        Assertions.assertThat(productCategory.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName Return Product Category When Successful")
    void findByName_ReturnProductCategory_WhenSuccessful(){
        ProductCategory savedProductCategory = productCategoryRepository.save(ProductCategoryCreator.createProductCategoryValid());
        String expectedName = savedProductCategory.getName();

        String url = String.format("/products/categorys/find?name=%s", expectedName);
        ProductCategory productCategory = testRestTemplate.getForObject(url, ProductCategory.class);

        Assertions.assertThat(productCategory)
                .isNotNull()
                .isEqualTo(savedProductCategory);
    }

    @Test
    @DisplayName("save Return Product Category When Successful")
    void save_ReturnProductCategory_WhenSuccessful(){
        ProductCategory category = ProductCategoryCreator.createProductCategoryValid();
        ResponseEntity<ProductCategory> productCategoryResponseEntity =
                testRestTemplate.postForEntity("/products/categorys", category, ProductCategory.class);

        Assertions.assertThat(productCategoryResponseEntity).isNotNull();
        Assertions.assertThat(productCategoryResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(productCategoryResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(productCategoryResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("replace update Product Category When Successful")
    void replace_UpdateProductCategory_WhenSuccessful(){
        ProductCategory savedProductCategory = productCategoryRepository.save(ProductCategoryCreator.createProductCategoryValid());
        savedProductCategory.setName("new name");
        ResponseEntity<Void> productCategoryResponseEntity = testRestTemplate.exchange("/products/categorys", HttpMethod.PUT,
                new HttpEntity<>(savedProductCategory), Void.class);

        Assertions.assertThat(productCategoryResponseEntity).isNotNull();
        Assertions.assertThat(productCategoryResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete Remove Product Category When Successful")
    void delete_RemoveProductCategory_WhenSuccessful(){
        ProductCategory savedProductCategory = productCategoryRepository.save(ProductCategoryCreator.createProductCategoryValid());

        ResponseEntity<Void> productCategoryResponseEntity = testRestTemplate.exchange("/products/categorys/{id}",
                HttpMethod.DELETE, null, Void.class, savedProductCategory.getId());

        Assertions.assertThat(productCategoryResponseEntity).isNotNull();
        Assertions.assertThat(productCategoryResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    //Not Successful cases
    @Test
    @DisplayName("findByName Return Null When Product Category is not found")
    void findByName_ReturnNull_WhenProductCategoryIsNotFound(){
        String url = String.format("/products/categorys/find?name=%s", "notExcists");
        ProductCategory productCategory = testRestTemplate.getForObject(url, ProductCategory.class);

        Assertions.assertThat(productCategory.getId())
                .isNull();
    }
}
