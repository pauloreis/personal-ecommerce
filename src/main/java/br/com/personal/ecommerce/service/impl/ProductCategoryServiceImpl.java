package br.com.personal.ecommerce.service.impl;

import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.repository.ProductCategoryRepository;
import br.com.personal.ecommerce.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> listAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProductCategory not found.")
        );
    }

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public void replace(ProductCategory productCategory) {
        var category = productCategoryRepository.findById(productCategory.getId());
        category.get().setName(productCategory.getName());
        productCategoryRepository.save(category.get());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productCategoryRepository.delete(this.findById(id));
    }

    @Override
    public ProductCategory findByName(String name) {
        return productCategoryRepository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProductCategory not found.")
        );
    }
}
