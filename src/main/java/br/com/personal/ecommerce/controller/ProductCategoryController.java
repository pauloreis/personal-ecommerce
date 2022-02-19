package br.com.personal.ecommerce.controller;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categorys")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return new ResponseEntity(productCategoryService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity(productCategoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductCategory productCategory){
        return new ResponseEntity(productCategoryService.save(productCategory), HttpStatus.CREATED);
    }
}
