package dev.vishal.productservice.repositories;

import dev.vishal.productservice.models.Category;
import dev.vishal.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, UUID> {

    @Override
    void deleteById(UUID uuid);

    // get list of product by id
    @Override
    Optional<Product> findById(UUID uuid);

    //get product with a specific title
    // internally spring data jpa will create sql queries for us and execute it when we run the following functions
    Product findByTitleEquals(String title);

    // get product with specific title and price of price obj
    Product findByTitleEqualsAndPrice_Price(String title, double price);

    // get list of products from currency of price obj
    List<Product> findAllByPrice_Currency(String currency);

    // count all products with a particular currency
    long countAllByPrice_Currency(String currency);

    // we will read all products with a particular currency
    List<Product> readAllByPrice_Currency(String currency);

    // if we want to write manual query and dont want spring jpa to create queries
    // then we will use @Query above our function definition
    // here :naman will fetch the value from function definition in the sql query
    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAllByTitle(String naman);

    // we have wrote query in hibernate query language and we will write to entities here
    // similar to sql but not same
    // in select we cannot give *, we have to give the object we want in HQL
    // advantage is HQL is compile time safety code since hibernate knows this classess and values exists
//    @Query("select Product  from Product where Product.price.currency = :currency and Product.title = :naman")
//    List<Product> readAllByTitle(String naman, String currency);

    List<Product> findAllByTitleLike(String titleRegex);

    List<Product> readAllByTitleLike(String titleRegex);

    // get lis of products from list of categories
    List<Product> findAllByCategoryIn(List<Category> categories);

}
