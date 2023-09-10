package dev.vishal.productservice.thirdpartyclients.productservice.fakestore;
import dev.vishal.productservice.dtos.GenericProductDto;
import dev.vishal.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
/***
 * Wrapper over FakeStore API
 */
@Service // this annotation means this is special class and spring will automatically create objects for this when application runs
public class FakeStoreProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private String specificProductRequestUrl ;
    private String productRequestsBaseUrl ;


    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestsBaseUrl  = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                productRequestsBaseUrl, product, FakeStoreProductDto.class);

        return response.getBody();

    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        // Fetch JSON response as String wrapped in ResponseEntity of FakeStoreProductDto type
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if (fakeStoreProductDto == null){
            throw new NotFoundException("product with id = " + id  + " doesn't exists");
        }

        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        // due to erasure at runtime java cannot find the type of list created
        // thats why we are using array of FakeStoreProductDto[]
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);

        return Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDto deleteProductById(Long id) {

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
        //return deleted product of FakeStoreDto type
        return fakeStoreProductDto;
    }
    public FakeStoreProductDto updatePrductById(GenericProductDto genericProductDto, Long id) {
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
        //return updated product of GenericProductDto type
        return fakeStoreProductDto;
    }
}
