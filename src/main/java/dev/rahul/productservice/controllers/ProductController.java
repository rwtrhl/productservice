package dev.rahul.productservice.controllers;


import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.models.Product;
import dev.rahul.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")

public class ProductController {
    // Field injection (Not recommended)
//    @Autowired
    ProductService productService;
    // Constructor injection
    public ProductController(@Qualifier("FakeStoreProductServiceImpl") ProductService productService){
        this.productService = productService;
    }
    // Setter injection (Not recommended)
//    public void setProductService(ProductService productService){
//        this.productService = productService;
//    }

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductId(@PathVariable("id") Long id){

    }

    @PostMapping
    public String createProduct(){
        return "Created a product with id " + UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }
}
