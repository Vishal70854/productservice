package dev.vishal.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vishal.productservice.dtos.GenericProductDto;
import dev.vishal.productservice.services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// not initialize any unnecessary bean
// only initialize dependencies that can be reached from ProductController
// @WebMvcTest is faster than @SpringBootTest as in WebMvcTest it will not load all spring dependencies at once. it will only load the dependencies needed and the one we mentioned
@WebMvcTest(ProductController.class) // here we are telling spring that we will do WebMvcTest for product controller class only.
public class ProductControllerWebMvcTest {
    @Autowired // here it will autowire only related dependency of MockMvc unlike springboottest which will load all controller, service and everything which makes it slow.
    private MockMvc mockMvc;

    @MockBean // @MockBean means If no bean of the same type is defined, a new one will be added. This annotation is useful in integration tests where a particular bean, like an external service, needs to be mocked.
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper internally used to convert java objects to json. it is done via a library name Jackson

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {
        when(
                productServiceImpl.getAllProducts()
        ).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(status().is(404))
                .andExpect(content().string("[]"));
    }

    @Test
    void returnsListOfProductsWhenProductsExist() throws Exception {
        ArrayList<GenericProductDto> products = new ArrayList<>();
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());

        when(
                productServiceImpl.getAllProducts()
        ).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
    }

    @Test
    void createProductShouldCreateANewProduct() throws Exception {
        GenericProductDto productToCreate = new GenericProductDto();
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImage("some image");
        productToCreate.setCategory("mobile phones");
        productToCreate.setDescription("Best iPhone Ever");

        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setId(1001L);
        expectedProduct.setTitle("iPhone 15 Pro Max");
        expectedProduct.setImage("some image");
        expectedProduct.setCategory("mobile phones");
        expectedProduct.setDescription("Best iPhone Ever");

        when(
                productServiceImpl.createProduct(any())
        ).thenReturn(expectedProduct);

        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate))
        ).andExpect(
                content().string(objectMapper.writeValueAsString(expectedProduct))
        ).andExpect(status().is(200));
    }


}
