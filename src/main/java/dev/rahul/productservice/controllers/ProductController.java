package dev.rahul.productservice.controllers;


import dev.rahul.productservice.dtos.ExceptionDto;
import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.exceptions.NotFoundException;
import dev.rahul.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException{
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductId(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product){
        return productService.updateProductById(id, product);
    }
}
