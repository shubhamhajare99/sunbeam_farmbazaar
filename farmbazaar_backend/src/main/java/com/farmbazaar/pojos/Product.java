package com.farmbazaar.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	
	@NotBlank
	private String name;
	
    private double price;
    private double quantity;
    
    @Lob
    private byte[] image;
    
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
//    @ManyToMany(mappedBy = "products")
//    private List<Farmer> farmers;
	
    public Double getPriceForCustomer(Customer customer) {
        
            return price * 1.10;  // 10% increase for customers
        
    }
    
    
    
   /* public Double getPriceForUser(User user) {
        if (user.getRole() == UserRole.CUSTOMER) {
            return basePrice * 1.10;  // 10% increase for customers
        }
        return basePrice;  // Farmers pay the base price
    }*/

	
}
