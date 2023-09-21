package dev.vishal.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
	private String title;
    private String description;
    private String image;
    //            P : C
    // => L to R: 1 : 1
    // => R to L: m : 1
    // => Ans:    m : 1
    @ManyToOne(cascade = {CascadeType.PERSIST}) // automatically save category object if not saved then save product obj
    @JoinColumn(name = "category") // used for altering column name. now it will be category instead of category_id
    private Category category;

    // cascade = {CascadeType.PERSIST} means if we have a object reference of a class inside another class i.e here its Category, Price is inside Product
    // then for saving to db we have to do so many calls . to remove this we will use cascade = {CascadeType.PERSIST} this means that
    // if we donot save some object then hibernate will automatically create and save category, price and then finally it will save product
    // CascadeType.PERSIST helps us by automatically creating new reference objects first followed by parent's object
    // CascadeType.REMOVE helps in removing the reference object first followed by parent i.e first it will remove price obj then product obj
    // and this will be done by hibernate automatically by mentioning cascade = {CascadeType.REMOVE}
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Price price;
}
