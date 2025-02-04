package com.farmbazaar.pojos;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    private String cat_name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
