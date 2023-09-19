package dev.vishal.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel{
	private String name;

	@OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
	private List<Product> products;
	// this is the same relation being mapped by category relation in other (Product) class
}
