package dev.vishal.productservice.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.vishal.productservice.dtos.FakeStoreProductDto;
import dev.vishal.productservice.dtos.GenericProductDto;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
	
	private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}"; // id is a variable
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    
    // a list of all products 
    List<GenericProductDto> products = new ArrayList<>();
    
    @Override
	public List<GenericProductDto> getAllProducts() {
		return products; 
	}

	@Override
	public GenericProductDto getProductById(Long id) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		// Fetch JSON response as String wrapped in ResponseEntity
		ResponseEntity<FakeStoreProductDto> response = 
				restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
		
		FakeStoreProductDto fakeStoreProductDto = response.getBody();
		
		GenericProductDto product = new GenericProductDto();
		product.setImage(fakeStoreProductDto.getImage());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setCategory(fakeStoreProductDto.getCategory());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setTitle(fakeStoreProductDto.getTitle());
		
		products.add(product); // added product in products list
		 
		return product;
	}

	@Override 
	public GenericProductDto createProduct(GenericProductDto product) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		// Fetch JSON response as String wrapped in ResponseEntity
		ResponseEntity<GenericProductDto> response = 
				restTemplate.postForEntity(createProductRequestUrl,product, GenericProductDto.class);
		
		GenericProductDto product1 = response.getBody();
		products.add(product1);
		return product1;
		
	}

	@Override
	public GenericProductDto updatePrductById(Long id) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		// Fetch JSON response as String wrapped in ResponseEntity
		ResponseEntity<GenericProductDto> response = 
				restTemplate.getForEntity(getProductRequestUrl, GenericProductDto.class, id);
		
		// get response entity in product
		GenericProductDto product = response.getBody();
		product.setPrice(1543.779);
		
		products.add(product);
		return product;
		
	}

	@Override
	public boolean deleteProductById(Long id) {
		Iterator<GenericProductDto> iterator = products.iterator();
		while(iterator.hasNext()) {
			Long currentId = iterator.next().getId();
			if (currentId == id) {
				iterator.remove();
				return true;
			}
		}
		return false;
		
	}

	

}
