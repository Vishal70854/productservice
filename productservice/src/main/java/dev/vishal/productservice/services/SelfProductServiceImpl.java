package dev.vishal.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.vishal.productservice.dtos.GenericProductDto;

@Service("selfProductServiceImpl") //it tells spring that this is service class with business logic
public class SelfProductServiceImpl implements ProductService {
	
	@Override
	public List<GenericProductDto> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public GenericProductDto getProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericProductDto updatePrductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deleteProductById(Long id) {
		// TODO Auto-generated method stub
		return true;
		
	}

	
}
