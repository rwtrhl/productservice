package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.FakeStoreProductDto;
import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.exceptions.NotFoundException;
import dev.rahul.productservice.thirpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Primary
@Service("FakeStoreProductServiceImpl")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        return convertToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        ArrayList<GenericProductDto> ans = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProducts()){
            ans.add(convertToGenericProductDto(fakeStoreProductDto));
        }
        return ans;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return convertToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, product));
    }


    @Override
    public GenericProductDto deleteProductById(Long id){
        return convertToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

}
