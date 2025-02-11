package com.farmbazaar.pojos;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int quantity;
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private Date updatedAt;
}
