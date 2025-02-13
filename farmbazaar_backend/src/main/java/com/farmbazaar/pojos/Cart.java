package com.farmbazaar.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Cart {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Cart_id;
	
    private double totalAmount;
	
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItemsList;

    @Column(name = "updated_at")
    private Date updatedAt;
    
    public Cart() {
        // Initialize cartItems list to avoid null pointer exception
        this.cartItemsList = new ArrayList<>();
    }
    
    public CartItem getCartItemByProductId(int productId) {
        Optional<CartItem> optionalCartItem = cartItemsList.stream()
                                               .filter(item -> item.getProduct().getPid() == productId)
                                               .findFirst();
        return optionalCartItem.orElse(null);
    }
    
    public void addCartItem(CartItem cartItem) {
        cartItem.setCart(this); // Associate the CartItem with this(current) Cart
        this.cartItemsList.add(cartItem); // Add the CartItem to the that Cart's list of items
    }
    public void calculateTotalAmount() {
        double totalAmount = 0.0;
        for (CartItem item : this.cartItemsList) {
        	totalAmount += item.getPrice() * item.getQuantity();
        }
        this.totalAmount = totalAmount;
    }
    
    public void clearCart() {
        this.cartItemsList.clear(); // Clear the cart items list
        this.totalAmount = 0.0; // Reset the total amount
    }


}
