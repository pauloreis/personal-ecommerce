package br.com.personal.ecommerce.repository;

import br.com.personal.ecommerce.domain.ProductPrize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPrizeRepository extends JpaRepository<ProductPrize, Long> {
}
