package br.com.personal.ecommerce.controller;

import br.com.personal.ecommerce.domain.Product;
import br.com.personal.ecommerce.domain.ProductCategory;
import br.com.personal.ecommerce.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products/categorys")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategory>> list() {
        return new ResponseEntity(productCategoryService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> findById(@PathVariable Long id) {
        return new ResponseEntity(productCategoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<ProductCategory> findByName(@RequestParam String name){
        return new ResponseEntity<>(productCategoryService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid ProductCategory productCategory){
        return new ResponseEntity(productCategoryService.save(productCategory), HttpStatus.CREATED);
    }
}
