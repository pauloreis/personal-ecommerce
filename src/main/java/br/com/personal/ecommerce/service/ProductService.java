package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;

import java.util.List;

public interface ProductService {
    List<Product> listAll();

    Product findById(Long id);

    Product save(ProductPostDto productPostDto);

    void delete(Long id);

    void replace(ProductPutDto productPutDto);
}
