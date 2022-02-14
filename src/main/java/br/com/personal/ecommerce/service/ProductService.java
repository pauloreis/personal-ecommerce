package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAll();

    Product findById(Long id);
}
