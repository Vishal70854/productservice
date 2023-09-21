package dev.vishal.productservice.repositories;

import dev.vishal.productservice.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Lazy
public interface CategoryRepository
        extends JpaRepository<Category, UUID> {

    Optional<Category> findById(UUID uuid);

    Category findByName(String categoryName);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);
}
