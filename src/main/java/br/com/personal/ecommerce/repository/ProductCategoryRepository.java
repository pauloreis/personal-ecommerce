package br.com.personal.ecommerce.repository;

import br.com.personal.ecommerce.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByName(String name);
}
