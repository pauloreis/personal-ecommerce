package br.com.personal.ecommerce.service;

import br.com.personal.ecommerce.domain.ProductPrize;

public interface ProductPrizeService {
    ProductPrize save(ProductPrize productPrize);

    ProductPrize findById(Long id);

    void replace(Long id);
}
