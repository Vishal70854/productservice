package dev.vishal.productservice.controllers;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import dev.vishal.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.vishal.productservice.dtos.GenericProductDto;
import dev.vishal.productservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	// @Autowired
	// field injection
	private ProductService productService;

	// tells spring which implementation class to inject by mentioning
	// @Qualalifier(className)
	// constructor injection
	// @Autowired
	public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
		this.productService = productService;
	}

	@PostMapping()
	public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
		return productService.createProduct(product);
	}

	// localhost:8080/products/{id}
	// localhost:8080/products/123
	@GetMapping("/{id}") // here id is a variable which will change based on user input
	public GenericProductDto getProductById(@PathVariable("id") long id) throws NotFoundException {
		return productService.getProductById(id);

	}

	@GetMapping
	public List<GenericProductDto> getAllProducts() {

		return productService.getAllProducts();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {

		return new ResponseEntity<>(
				productService.deleteProductById(id),
				HttpStatus.OK // status code which we can send manually
		);
	}

	@PutMapping("/{id}")
	public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto, @PathVariable("id") Long id) {

		return productService.updatePrductById(genericProductDto,id);
	}
}
