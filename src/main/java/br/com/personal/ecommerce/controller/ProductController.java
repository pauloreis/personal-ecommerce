package br.com.personal.ecommerce.controller;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.dto.ProductPostDto;
import br.com.personal.ecommerce.dto.ProductPutDto;
import br.com.personal.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> list(Pageable pageable) {
        return new ResponseEntity(productService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid ProductPostDto productPostDto){
        return new ResponseEntity(productService.save(productPostDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid ProductPutDto productPutDto){
        productService.replace(productPutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
