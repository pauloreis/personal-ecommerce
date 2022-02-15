package br.com.personal.ecommerce.repository.impl;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static br.com.personal.ecommerce.domain.ProductCategory.ELETRONICO;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static List<Product> products;

    static {
        Product p1 = new Product(1L, UUID.randomUUID(), "Televisão Sansung", ELETRONICO, 2.500);
        Product p2 = new Product(2L, UUID.randomUUID(), "Microondas LG", ELETRONICO, 480D);
        Product p3 = new Product(3L, UUID.randomUUID(), "Caixa de Som JBL", ELETRONICO, 1.500);
        products = new ArrayList<>(List.of(p1, p2, p3));
    }

    @Override
    public List<Product> listAll() {
        return this.products;
    }

    @Override
    public Product findById(Long id) {
        return this.products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Not Found."));
    }

    @Override
    public Product save(Product product) {
        product.setId(ThreadLocalRandom.current().nextLong(3, 999));
        product.setUuid(UUID.randomUUID());
        products.add(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        products.remove(this.findById(id));
    }

    @Override
    public void replace(Product product) {
        this.delete(product.getId());
        products.add(product);
    }
}
