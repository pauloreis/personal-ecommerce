package br.com.personal.ecommerce.controller;

import br.com.personal.ecommerce.domain.ProductPrize;
import br.com.personal.ecommerce.service.ProductPrizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products/prizes")
@RequiredArgsConstructor
public class ProductPrizeController {

    private final ProductPrizeService productPrizeService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductPrize> findById(@PathVariable Long id){
        return new ResponseEntity(productPrizeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductPrize> save(@RequestBody @Valid ProductPrize productPrize){
        return new ResponseEntity(productPrizeService.save(productPrize), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productPrizeService.replace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
