package dev.vishal.productservice.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dev.vishal.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import dev.vishal.productservice.dtos.FakeStoreProductDto;
import dev.vishal.productservice.dtos.GenericProductDto;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
	
	private RestTemplateBuilder restTemplateBuilder;
	private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
	private String productRequestsBaseUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    
    // a list of all products 
	public GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){
		GenericProductDto product = new GenericProductDto();
		product.setId(fakeStoreProductDto.getId());
		product.setImage(fakeStoreProductDto.getImage());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setCategory(fakeStoreProductDto.getCategory());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setTitle(fakeStoreProductDto.getTitle());

		return product;
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto product) {

		RestTemplate restTemplate = restTemplateBuilder.build();

		// Fetch JSON response as String wrapped in ResponseEntity
		ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
				productRequestsBaseUrl, product, GenericProductDto.class
		);

		return response.getBody();

	}

	@Override
	public GenericProductDto getProductById(Long id) throws NotFoundException {
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Fetch JSON response as String wrapped in ResponseEntity of FakeStoreProductDto type
		ResponseEntity<FakeStoreProductDto> response =
				restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

		FakeStoreProductDto fakeStoreProductDto = response.getBody();

		if (fakeStoreProductDto == null){
			throw new NotFoundException("product with id = " + id  + " doesn't exists");
		}
		// convert to GenericProductType and return the product
		GenericProductDto product = convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);

		return product;
	}

	@Override
	public List<GenericProductDto> getAllProducts() {

		RestTemplate restTemplate = restTemplateBuilder.build();
		// due to erasure at runtime java cannot find the type of list created
		// thats why we are using array of FakeStoreProductDto[]
		ResponseEntity<FakeStoreProductDto[]> response =
				restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);

		List<GenericProductDto> answer = new ArrayList<>();

		for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){
			answer.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
		}

		return answer;
	}

	@Override
	public GenericProductDto deleteProductById(Long id) {

		RestTemplate restTemplate = restTemplateBuilder.build();

		// we are using getForEntity() definition because update return type is
		// void thus we want to return object back to API.
		// using getForEntity() actual values and changed it according to our requirements
		// below code create response entity of type FakeStoreProductDto and we will
		// get it through id thats why used getForEntity() definition and finally
		// return GenericProductDto object type i.e deleted object

		RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
				restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
		ResponseEntity<FakeStoreProductDto> response =
				restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE,
						requestCallback, responseExtractor, id);

		FakeStoreProductDto fakeStoreProductDto = response.getBody();
		//return deleted product
		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
	}
	@Override
	public GenericProductDto updatePrductById(GenericProductDto genericProductDto, Long id) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		/* not implemented by this
		// using getForEntity() actual values and changed it according to our requirements
//		RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback
//				(FakeStoreProductDto.class);
//		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
//				restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
//		ResponseEntity<FakeStoreProductDto> response =
//				restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT,
//						requestCallback, responseExtractor, id);*/

		// we are using postForEntity() definition because update return type is
		// void thus we want to return object back to API.
		// we are using postForEntity implementation because we are accepting a
		// genericproductDto object in httpEntityCallback() mentioned below
		// then we will get Fakestoreproductdto object entity in response and
		// we can edit all the fields of genericproductdto as per our requirements.

		RequestCallback requestCallback = restTemplate.
				httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
				restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
		ResponseEntity<FakeStoreProductDto> response =
				restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

		FakeStoreProductDto fakeStoreProductDto = response.getBody();
		// update GenericProductDto fields
		genericProductDto.setId(fakeStoreProductDto.getId());
		genericProductDto.setTitle(fakeStoreProductDto.getTitle());
		genericProductDto.setPrice(fakeStoreProductDto.getPrice());
		genericProductDto.setImage(fakeStoreProductDto.getImage());
		genericProductDto.setCategory(fakeStoreProductDto.getCategory());
		genericProductDto.setDescription(fakeStoreProductDto.getDescription());

		return genericProductDto;
	}
	

}
