package com.farmbazaar.pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // @Column(name = "cat_name")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}
