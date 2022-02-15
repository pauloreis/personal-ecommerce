package br.com.personal.ecommerce.repository;

import br.com.personal.ecommerce.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> listAll();

    Product findById(Long id);

    Product save(Product product);

    void delete(Long id);

    void replace(Product product);
}
