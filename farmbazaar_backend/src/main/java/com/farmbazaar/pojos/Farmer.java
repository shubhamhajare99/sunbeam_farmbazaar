package com.farmbazaar.pojos;

import java.util.List;



import jakarta.persistence.*;

@Entity
public class Farmer extends User {
	
	@ManyToMany
    @JoinTable(name = "farmer_product",
               joinColumns = @JoinColumn(name = "farmer_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    //@JsonIgnore // Exclude products from serialization to prevent recursion
    private List<Product> productsList;

    private double totalProfit;
    

}
