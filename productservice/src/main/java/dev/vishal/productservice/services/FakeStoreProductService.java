package dev.vishal.productservice.services;

import java.util.ArrayList;
import java.util.List;

import dev.vishal.productservice.exceptions.NotFoundException;
import dev.vishal.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import dev.vishal.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.vishal.productservice.dtos.GenericProductDto;
//@Primary	//@primary means spring will look for this class when searching for business logic
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
	private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
		this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
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

		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));

	}

	@Override
	public GenericProductDto getProductById(Long id) throws NotFoundException {
		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
	}

	@Override
	public List<GenericProductDto> getAllProducts() {
		List<GenericProductDto> genericProductDtos = new ArrayList<>();

		for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProducts()){
			genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
		}

		return genericProductDtos;
	}

	@Override
	public GenericProductDto deleteProductById(Long id) {

		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));
	}
	@Override
	public GenericProductDto updatePrductById(GenericProductDto genericProductDto, Long id) {

		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.updatePrductById(genericProductDto, id));
	}
	

}
