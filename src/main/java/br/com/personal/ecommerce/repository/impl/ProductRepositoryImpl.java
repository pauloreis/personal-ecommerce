package br.com.personal.ecommerce.repository.impl;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> lista;

    public ProductRepositoryImpl() {
        ProductCategory pc = new ProductCategory(1L, UUID.randomUUID(), "Eletrônico");
        Product p1 = new Product(1L, UUID.randomUUID(), "Televisão Sansung", pc, 2.500);
        Product p2 = new Product(2L, UUID.randomUUID(), "Microondas LG", pc, 480D);
        Product p3 = new Product(3L, UUID.randomUUID(), "Caixa de Som JBL", pc, 1.500);
        this.lista = List.of(p1, p2, p3);
    }

    @Override
    public List<Product> listAll() {
        return this.lista;
    }

    @Override
    public Product findById(Long id) {
        return this.lista.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Not Found."));
    }
}
