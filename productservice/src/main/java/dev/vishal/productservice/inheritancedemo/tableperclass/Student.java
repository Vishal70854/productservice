package dev.vishal.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_student")
@DiscriminatorValue(value = "1")
public class Student extends User {
    private  double psp;
    private double attendance;
}
