package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("SelfProductServiceImpl")
public class SelfProductService implements ProductService{

        @Override
        public GenericProductDto getProductById(Long id) {
            return null;
        }
}
