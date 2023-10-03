package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.exceptions.NotFoundException;
import dev.rahul.productservice.models.Product;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto updateProductById(Long id, GenericProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id);
}
