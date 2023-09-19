package dev.vishal.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_mentor")
@DiscriminatorValue(value = "3")
public class Mentor extends User {
    private  double averageRating;
}
