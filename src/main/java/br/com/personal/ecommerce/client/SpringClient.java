package br.com.personal.ecommerce.client;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.domain.ProductCategory;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class SpringClient {

    public static void main(String[] args) {
        String url = "http://localhost:8080/products/{id}";
        String url1 = "http://localhost:8080/products";
        String url2 = "http://localhost:8080/products/categorys";

        //List
        ResponseEntity<Product> entity = new RestTemplate().getForEntity(url, Product.class, 1);
        log.info("List Entity: {}" +entity);

        //List
        Product object = new RestTemplate().getForObject(url, Product.class, 1);
        log.info("List Object: {}" + object);

        //List
        ResponseEntity<List<ProductCategory>> exchange =
                new RestTemplate().exchange(url2, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        log.info("List Exchange: {}" + exchange.getBody());

        //Post
        ProductCategory category = ProductCategory.builder()
                .name("Television")
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        // ProductCategory eletronic = new RestTemplate().postForObject(url2, category, ProductCategory.class);

        //Post com exchange
        ResponseEntity<ProductCategory> tv = new RestTemplate()
                .exchange(url2, HttpMethod.POST,
                        new HttpEntity<>(category, createJsonHeader()), ProductCategory.class);

        log.info("Saved ProductCategory: {} " + tv);

        //Exchange for Update and Delete
        //Usualy use exchange

        //List With Pagination Parameters
        Map<String, String> params = new HashMap<>();
        params.put("size", "50");
        params.put("page", "0");
        params.put("sort", "name,desc");

        ResponseEntity<PaginatedResponse<Product>> exchangePage = new RestTemplate().exchange(
                url1, HttpMethod.GET, null,
                new ParameterizedTypeReference<PaginatedResponse<Product>>() {
                }, params);
        log.info("List Exchange Pagination: {}" + exchangePage.getBody().getContent());

    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
