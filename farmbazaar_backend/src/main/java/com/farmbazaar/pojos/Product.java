package com.farmbazaar.pojos;

import java.util.Base64;
import java.util.List;

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
	private Integer id;
	
	@NotBlank
	private String name;
	
    private double price;
    private double quantity;
    
    @Lob
    private byte[] image; // Byte array to store image data
    
    @Transient
    private String imageBase64; // Attribute to store Base64-encoded image data
    
    
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

    public void encodeImageDataToBase64() {
        if (image != null) {
            this.imageBase64 = Base64.getEncoder().encodeToString(image);
        }
    }
}
