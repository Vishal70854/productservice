package dev.vishal.productservice.repositories;

import dev.vishal.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository
        extends JpaRepository<Price, Long> {
}