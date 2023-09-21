package dev.vishal.productservice;

import dev.vishal.productservice.inheritancedemo.joinedtable.Mentor;
import dev.vishal.productservice.inheritancedemo.joinedtable.MentorRepository;
import dev.vishal.productservice.inheritancedemo.joinedtable.User;
import dev.vishal.productservice.inheritancedemo.joinedtable.UserRepository;
import dev.vishal.productservice.models.Category;
import dev.vishal.productservice.models.Price;
import dev.vishal.productservice.models.Product;
import dev.vishal.productservice.repositories.CategoryRepository;
import dev.vishal.productservice.repositories.PriceRepository;
import dev.vishal.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication { // implements CommandLineRunner {

//	private MentorRepository mentorRepository;
//	private UserRepository userRepository;
//	private final ProductRepository productRepository;
//	private final CategoryRepository categoryRepository;
//	private final PriceRepository priceRepository;

//	public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,
//									 @Qualifier("jt_ur") UserRepository userRepository,
//									 ProductRepository productRepository,
//									 CategoryRepository categoryRepository,
//									 PriceRepository priceRepository) {
//		this.mentorRepository = mentorRepository;
//		this.userRepository = userRepository;
//		this.productRepository = productRepository;
//		this.categoryRepository = categoryRepository;
//		this.priceRepository = priceRepository;
//	}

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
////		Mentor mentor = new Mentor();
////		mentor.setName("Naman");
////		mentor.setEmail("Naman@scaler.com");
////		mentor.setAverageRating(4.65);
////		mentorRepository.save(mentor);
////
////		User user = new User();
////		user.setName("Sarath");
////		user.setEmail("sarathcool@yopmail.com");
////		userRepository.save(user);
////
////		List<User> users = userRepository.findAll();
////		for (User user1 : users) {
////			System.out.println(user1);
////		}
//
//		Category category = new Category();
//		category.setName("Apple Devices");
////		Category savedCategory =  categoryRepository.save(category);
//
//		Price price = new Price("Ruppee", 10);
////		Price savedPrice =  priceRepository.save(price);
//
//		Product product = new Product();
//		product.setTitle("iphone 15 pro");
//		product.setDescription("the best iphone ever");
//		product.setCategory(category);
//		product.setPrice(price);
//		productRepository.save(product);
//
//		productRepository.deleteById(UUID.fromString("dfe05540-6fc1-4b97-bcad-7812820895cd"));
//
//		System.out.println(productRepository.countAllByPrice_Currency("Ruppee"));
//		List<Product> products = productRepository.findAllByPrice_Currency("Ruppee");
//
////		Category category1 = categoryRepository.findById(UUID.fromString("1105cf12-bbc4-4711-95f9-a7c5034c63a1")).get();
////		System.out.println("category name is : "+category1);
////		System.out.println("Printing all products in the category");
//
////		for(Product product1 : category1.getProducts()){
////			try{
////				System.out.println(product1.getTitle());
////			} catch (Exception e){
////				System.out.println(e.getMessage());
////			}
////
////		}
//
//		List<Product> products1 = productRepository.findAllByTitle("iphone 15 pro");
//
//        System.out.println("Fetching category b8f1f459-f9e9-4d3d-b115-f1f5267bd54f");
//        Thread.sleep(1000);
//        Category category1 = categoryRepository.findById(UUID.fromString("b8f1f459-f9e9-4d3d-b115-f1f5267bd54f")).get();
////        Category category1 = category1Optional.get();
//
//        System.out.println("Fetching products for category");
//        Thread.sleep(1000);
//        List<Product> products11 = category1.getProducts();
//    }
}
