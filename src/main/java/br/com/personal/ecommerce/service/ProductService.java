package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> listAll(Pageable pageable);

    Product findById(Long id);

    Product save(ProductPostDto productPostDto);

    void delete(Long id);

    void replace(ProductPutDto productPutDto);
}
