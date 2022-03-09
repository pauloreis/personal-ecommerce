package br.com.personal.ecommerce.service.impl;

import br.com.personal.ecommerce.domain.ProductPrize;
import br.com.personal.ecommerce.repository.ProductPrizeRepository;
import br.com.personal.ecommerce.service.ProductPrizeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ProductPrizeServiceImpl implements ProductPrizeService {

    private final ProductPrizeRepository productPrizeRepository;

    @Override
    public ProductPrize save(ProductPrize productPrize) {
        return productPrizeRepository.save(productPrize);
    }

    @Override
    public ProductPrize findById(Long id) {
        return productPrizeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "ProductPriz not found.")
        );
    }
}
