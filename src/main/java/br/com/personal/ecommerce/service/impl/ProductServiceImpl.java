package br.com.personal.ecommerce.service.impl;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;
import br.com.personal.ecommerce.repository.ProductCategoryRepository;
import br.com.personal.ecommerce.repository.ProductRepository;
import br.com.personal.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found.")
        );
    }

    @Override
    public Product save(ProductPostDto productPostDto) {
        ProductCategory category = productCategoryRepository.findById(Long.valueOf(productPostDto.getCategory()))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProductCategory not found.")
                );
        Product product = Product.converter(productPostDto);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(this.findById(id));
    }

    @Override
    public void replace(ProductPutDto productPutDto) {
        Product product = this.findById(productPutDto.getId());
        productRepository.save(Product.converter(productPutDto, product.getId()));
    }
}
