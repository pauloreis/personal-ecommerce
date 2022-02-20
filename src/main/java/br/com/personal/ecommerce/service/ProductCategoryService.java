package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> listAll();

    ProductCategory findById(Long id);

    ProductCategory save(ProductCategory productCategory);

    ProductCategory findByName(String name);
}
