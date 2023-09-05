package dev.vishal.productservice.services;

import java.util.List;

import dev.vishal.productservice.dtos.GenericProductDto;

public interface ProductService {
	// get all products
	List<GenericProductDto> getAllProducts();
		
	// abstract method for get product by id
	GenericProductDto getProductById(Long id);
	
	// abstract method for create product
	GenericProductDto createProduct(GenericProductDto product);

	// abstract method for update product by id
	GenericProductDto updatePrductById(Long id);
	
	// abstract method to delete product by id
	boolean deleteProductById(Long id);
	
}
