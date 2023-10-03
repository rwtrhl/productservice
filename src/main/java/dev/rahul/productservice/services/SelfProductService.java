package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductServiceImpl")
public class SelfProductService implements ProductService{

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product){
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id){
        return null;
    }

}
