package br.com.personal.ecommerce.repository;

import br.com.personal.ecommerce.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
