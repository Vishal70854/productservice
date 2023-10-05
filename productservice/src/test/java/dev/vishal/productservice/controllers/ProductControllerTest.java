package dev.vishal.productservice.controllers;

import dev.vishal.productservice.dtos.GenericProductDto;
import dev.vishal.productservice.exceptions.NotFoundException;
import dev.vishal.productservice.services.FakeStoreProductService;
import dev.vishal.productservice.services.ProductServiceImpl;
import dev.vishal.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private FakeStoreProductService fakeStoreProductService;

    // we are manually hardcoding null as output when a product doesn't exists.
    // this is done inorder to perform unit testing with the help of mocking where we are manually giving hardcoded values for all the
    // dependencies apart from the current class values.
    // we will give values for all other dependencies for which we might have to talk to other modules/class or other third party apis
    @Test
    void returnsNullWhenProductDoesntExist() throws NotFoundException {
        when(
                productServiceImpl.getProductById(any(UUID.class))
        ).thenReturn(null);

        GenericProductDto genericProductDto = productController.getProductById(UUID.fromString("960bf85a-7da6-4489-83d6-c371303ba6b1"));

        assertNull(genericProductDto);
    }

//    @Test // in Naman sir example it is shown for fakestoreproductservice but i have implementation for selfproductservice
//    void throwsExceptionWhenProductDoesntExist() throws NotFoundException {
//        when(
//                productServiceImpl.getProductById(any(UUID.class))
//        ).thenReturn(null);
//
//        assertThrows(NotFoundException.class, () ->productController.getProductById(UUID.fromString("960bf85a-7da6-4489-83d6-c371303ba6b1")));
//    }

    // here we are checking when a product exists and we are hardcoding values
    // then the same object will be returned to us.
    // this is beneficial for testing purpose.
    @Test
    void returnsSameProductAsServiceWhenProductExists() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();

        when(
                productServiceImpl.getProductById(any(UUID.class))
        ).thenReturn(genericProductDto);

        assertEquals(genericProductDto.getPrice(), productController.getProductById(UUID.fromString("960bf85a-7da6-4489-83d6-c371303ba6b1")).getPrice());
    }

    // here we are hardcoding genericProductDto title value and as soon as the getProductById() method is called
    // the genericProductDto which we created will be called and returned
    // this is mocking where we are hardcoding values which are dependencies from other classes or other apis.
    @Test
    void shouldReturnTitleNamanWithProductID1() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle("Naman");
        when(
                productServiceImpl.getProductById(UUID.fromString("960bf85a-7da6-4489-83d6-c371303ba6b1"))
        ).thenReturn(
                genericProductDto
        );


        GenericProductDto genericProductDto1 = productController.getProductById(UUID.fromString("960bf85a-7da6-4489-83d6-c371303ba6b1"));
        assertEquals("Naman", genericProductDto1.getTitle());
    }

    @Test
    @DisplayName("1 + 1 equals 2")
    void onePlusOneEqualsTrue() throws NotFoundException {
//        System.out.println("it is true");

//        assertEquals(11, 1 + 1, "1 + 1 is not equal to 11");

//        assertNull(fakeStoreProductServiceClient.getProductById(101L));

//        assertEquals(null, fakeStoreProductServiceClient.getProductById(101L));

//        assertThrows(NotFoundException.class, () -> fakeStoreProductServiceClient.getProductById(101L));

//        assertEquals(true, 1 + 1 == 2);

    }

    boolean returnSomething() {
        Random random = new Random();
        return random.nextInt() % 2 == 0;
    }

    @Test
    void additionShouldBeCorrect() {
        assertTrue(-1 + -1 == -2, "adding 2 negatives is not correct");

        assertTrue(-1 + 0 == -1, "adding a negative and a zero is giving wrong answer");

        assertTrue(-1 + 1 == 0);

        assert 1 + 0 == 1;

        assert 1 + 1 == 2;
    }

}
// Assertion Framework
// -> allows you to make assertions
// -> allows you to make checks