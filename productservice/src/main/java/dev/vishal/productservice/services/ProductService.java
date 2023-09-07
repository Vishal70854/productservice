package dev.vishal.productservice.services;

import java.util.List;

import dev.vishal.productservice.dtos.GenericProductDto;
import dev.vishal.productservice.exceptions.NotFoundException;

public interface ProductService {
	// abstract method for create product
	GenericProductDto createProduct(GenericProductDto product);

	// abstract method for get product by id
	GenericProductDto getProductById(Long id) throws NotFoundException;

	// get all products
	List<GenericProductDto> getAllProducts();

	// abstract method to delete product by id
	GenericProductDto deleteProductById( Long id);

	// abstract method for update product by id
	GenericProductDto updatePrductById(GenericProductDto genericProductDto,Long id);

}
