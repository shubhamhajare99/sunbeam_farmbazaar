package com.farmbazaar.pojos;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonBackReference 
    private List<OrderItem> orderItems;
    
    private double totalAmount;
    private String deliveryAddress;
    private String deliveryStatus;
    private Date placedDate;

}
