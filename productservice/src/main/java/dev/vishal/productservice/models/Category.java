package dev.vishal.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel{
	private String name;

	// eager will fetch the category and products details also, lazy will only get category details
	@OneToMany(mappedBy = "category")
	@Fetch(FetchMode.SELECT)
	private List<Product> products;
	// this is the same relation being mapped by category relation in other (Product) class
}
