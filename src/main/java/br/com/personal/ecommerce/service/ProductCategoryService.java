package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> listAll();

    ProductCategory findById(Long id);

    ProductCategory findByName(String name);

    ProductCategory save(ProductCategory productCategory);

    void replace(ProductCategory productCategory);

    void delete(Long id);
}
