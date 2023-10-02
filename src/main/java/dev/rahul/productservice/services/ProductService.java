package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id);
}
