package br.com.personal.ecommerce.service.impl;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.domain.ProductPrize;
import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;
import br.com.personal.ecommerce.repository.ProductCategoryRepository;
import br.com.personal.ecommerce.repository.ProductPrizeRepository;
import br.com.personal.ecommerce.repository.ProductRepository;
import br.com.personal.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductPrizeRepository productPrizeRepository;

    @Override
    public Page<Product> listAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found.")
        );
    }

    @Transactional
    @Override
    public Product save(ProductPostDto productPostDto) {
        ProductCategory category = productCategoryRepository.findByName(productPostDto.getCategory())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProductCategory not found.")
                );

        ProductPrize prize = productPrizeRepository.findById(productPostDto.getPrizeId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProductPrize not found.")
                );

        Product product = Product.converter(productPostDto, category, prize);
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productRepository.delete(this.findById(id));
    }

    @Transactional
    @Override
    public void replace(ProductPutDto productPutDto) {
        Product product = this.findById(productPutDto.getId());

        ProductCategory category = productCategoryRepository.findByName(productPutDto.getCategory())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProductCategory not found.")
                );

        ProductPrize prize = productPrizeRepository.findById(productPutDto.getPrizeId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProductPrize not found.")
                );

        product.setCategory(category);
        product.setProductPrize(prize);

        productRepository.save(Product.converter(productPutDto, product));
    }
}
