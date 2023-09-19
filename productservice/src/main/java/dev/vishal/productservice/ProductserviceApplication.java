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
public class ProductserviceApplication implements CommandLineRunner {

	private MentorRepository mentorRepository;
	private UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final PriceRepository priceRepository;

	public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,
									 @Qualifier("jt_ur") UserRepository userRepository,
									 ProductRepository productRepository,
									 CategoryRepository categoryRepository,
									 PriceRepository priceRepository) {
		this.mentorRepository = mentorRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Mentor mentor = new Mentor();
//		mentor.setName("Naman");
//		mentor.setEmail("Naman@scaler.com");
//		mentor.setAverageRating(4.65);
//		mentorRepository.save(mentor);
//
//		User user = new User();
//		user.setName("Sarath");
//		user.setEmail("sarathcool@yopmail.com");
//		userRepository.save(user);
//
//		List<User> users = userRepository.findAll();
//		for (User user1 : users) {
//			System.out.println(user1);
//		}

		Category category = new Category();
		category.setName("Apple Devices");
		Category savedCategory =  categoryRepository.save(category);

		Product product = new Product();
		product.setTitle("iphone 15 pro");
		product.setDescription("the best iphone ever");
		product.setCategory(savedCategory);
		productRepository.save(product);

		Category category1 = categoryRepository.findById(UUID.fromString("1105cf12-bbc4-4711-95f9-a7c5034c63a1")).get();
		System.out.println("category name is : "+category1);
		System.out.println("Printing all products in the category");

		for(Product product1 : category1.getProducts()){
			try{
				System.out.println(product1.getTitle());
			} catch (Exception e){
				System.out.println(e.getMessage());
			}

		}
	}
}
