package br.com.personal.ecommerce.service.impl;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;
import br.com.personal.ecommerce.repository.ProductRepository;
import br.com.personal.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

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
        return productRepository.save(Product.converter(productPostDto));
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
