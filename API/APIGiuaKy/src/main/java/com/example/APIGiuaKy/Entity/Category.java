package com.example.APIGiuaKy.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String categoryName;

    private String avatar;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Product> products;
}