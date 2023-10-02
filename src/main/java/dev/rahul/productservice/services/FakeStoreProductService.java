package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.FakeStoreProductDto;
import dev.rahul.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service("FakeStoreProductServiceImpl")
public class FakeStoreProductService implements ProductService{

    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String updateProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products";
    private String deleteProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private RestTemplateBuilder restTemplateBuilder;



    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
//        response.getStatusCode()
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto product = new GenericProductDto();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }

    @Override
    public GenericProductDto[] getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto[]> response = restTemplate.getForEntity(getAllProductsRequestUrl, GenericProductDto[].class);
        return response.getBody();
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestUrl, product, GenericProductDto.class);

        return response.getBody();
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, GenericProductDto.class);
        ResponseExtractor<ResponseEntity<GenericProductDto>>responseExtractor = restTemplate.responseEntityExtractor(GenericProductDto.class);
        ResponseEntity<GenericProductDto> response = restTemplate.execute(updateProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return response.getBody();

//        RestTemplate restTemplate = restTemplateBuilder.build();
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
//        ResponseExtractor<ResponseEntity<GenericProductDto>> responseExtractor = restTemplate.responseEntityExtractor(GenericProductDto.class);
//        ResponseEntity<GenericProductDto> response = restTemplate.execute(updateProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id, product);
//        return response.getBody();
    }


    @Override
    public GenericProductDto deleteProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
        ResponseExtractor<ResponseEntity<GenericProductDto>> responseExtractor = restTemplate.responseEntityExtractor(GenericProductDto.class);
        ResponseEntity<GenericProductDto> response = restTemplate.execute(deleteProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return response.getBody();
    }
}
