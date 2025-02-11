package com.farmbazaar.pojos;

import jakarta.persistence.*;
import jakarta.persistence.OneToOne;
import lombok.*;


@Entity
public class Customer extends User {

	@OneToOne(mappedBy = "customer")
    private Cart cart;
	
	public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
