package dev.vishal.productservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import dev.vishal.productservice.dtos.CategoryDto;
import dev.vishal.productservice.dtos.ProductDto;
import dev.vishal.productservice.exceptions.NotFoundException;
import dev.vishal.productservice.models.Category;
import dev.vishal.productservice.services.ProductServiceImpl;
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

@RestController // this annotation means this class will handle all HTTP request/responses
@RequestMapping("/products")
public class ProductController {
	// @Autowired
	// field injection
	private ProductServiceImpl productServiceImpl;

	// tells spring which implementation class to inject by mentioning
	// @Qualifier(className)
	// constructor injection
	// @Autowired
	public ProductController(ProductServiceImpl productServiceImpl) {

		this.productServiceImpl = productServiceImpl;
	}

	@PostMapping()
	public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
		return productServiceImpl.createProduct(product);
	}

	// localhost:8080/products/{id}
	// localhost:8080/products/123
	@GetMapping("/{id}") // here id is a variable which will change based on user input
	public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
		return productServiceImpl.getProductById(id);

	}

	@GetMapping()
	public ResponseEntity<List<GenericProductDto>> getAllProducts() {

		List<GenericProductDto> productDtos = productServiceImpl.getAllProducts();
		if (productDtos.isEmpty()) {
			return new ResponseEntity<>(
					productDtos,
					HttpStatus.NOT_FOUND
			);
		}

		List<GenericProductDto> genericProductDtos = new ArrayList<>();

		for (GenericProductDto gpd: productDtos) {
			genericProductDtos.add(gpd);
		};

//        genericProductDtos.remove(genericProductDtos.get(0));

		return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);

//        productDtos.get(0).setId(1001L);
//
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) {

		return new ResponseEntity<>(
				productServiceImpl.deleteProductById(id),
				HttpStatus.OK // status code which we can send manually
		);
	}

	@PutMapping("/{id}")
	public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto, @PathVariable("id") UUID id) {

		return productServiceImpl.updatePrductById(genericProductDto,id);
	}

	@GetMapping("/categories/{uuid}")
	public List<ProductDto> getCategoryById(@PathVariable("uuid") String uuid){
		return productServiceImpl.getCategoryById(uuid);
	}

	// get all categories
	@GetMapping("/categories")
	public List<String> getAllCategories(){
		return productServiceImpl.getAllCategories();
	}

}
