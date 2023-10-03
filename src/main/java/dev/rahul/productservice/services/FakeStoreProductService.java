package dev.rahul.productservice.services;

import dev.rahul.productservice.dtos.FakeStoreProductDto;
import dev.rahul.productservice.dtos.GenericProductDto;
import dev.rahul.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("FakeStoreProductServiceImpl")
public class FakeStoreProductService implements ProductService{

    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;



    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
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
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
//        response.getStatusCode()
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto == null)
            throw new NotFoundException("Product with id: " + id + " not found");
        GenericProductDto product = convertToGenericProductDto(fakeStoreProductDto);
        return product;
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            ResponseEntity<FakeStoreProductDto[]>response = restTemplate.getForEntity(getAllProductsRequestUrl, FakeStoreProductDto[].class);

            List<GenericProductDto> answer = new ArrayList<>();
            for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){
                answer.add(convertToGenericProductDto(fakeStoreProductDto));
            }
            return answer;
        } catch (Exception e) {
            return Collections.emptyList();
        }
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
        ResponseExtractor<ResponseEntity<GenericProductDto>> responseExtractor = restTemplate.responseEntityExtractor(GenericProductDto.class);
        ResponseEntity<GenericProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return response.getBody();
    }


    @Override
    public GenericProductDto deleteProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
        ResponseExtractor<ResponseEntity<GenericProductDto>> responseExtractor = restTemplate.responseEntityExtractor(GenericProductDto.class);
        ResponseEntity<GenericProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return response.getBody();
    }

}
