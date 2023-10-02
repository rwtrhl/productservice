package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto updateProductById(Long id, GenericProductDto product);

    GenericProductDto[] getAllProducts();

    GenericProductDto deleteProductById(Long id);
}
