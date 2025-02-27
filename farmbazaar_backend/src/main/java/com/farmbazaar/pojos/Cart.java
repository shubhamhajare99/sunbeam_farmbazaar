package com.farmbazaar.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Cart {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
    private int id;
	
    private double totalAmount;
	
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore // Prevent Jackson from serializing this attribute
    private Customer customer;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore 
    private List<CartItem> cartItems;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    public Cart() {
        // Initialize cartItems list to avoid null pointer exception
        this.cartItems = new ArrayList<>();
    }
    
    public CartItem getCartItemByProductId(int productId) {
        Optional<CartItem> optionalCartItem = cartItems.stream()
                                               .filter(item -> item.getProduct().getId() == productId)
                                               .findFirst();
        return optionalCartItem.orElse(null);
    }
    
    public void addCartItem(CartItem cartItem) {
        cartItem.setCart(this); // Associate the CartItem with this(current) Cart
        this.cartItems.add(cartItem); // Add the CartItem to the that Cart's list of items
    }
    public void calculateTotalAmount() {
        double totalAmount = 0.0;
        for (CartItem item : this.cartItems) {
        	totalAmount += item.getPrice() * item.getQuantity();
        }
        this.totalAmount = totalAmount;
    }
    
    public void clearCart() {
        this.cartItems.clear(); // Clear the cart items list
        this.totalAmount = 0.0; // Reset the total amount
    }


}
