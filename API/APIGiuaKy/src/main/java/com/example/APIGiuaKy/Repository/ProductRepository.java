package com.example.APIGiuaKy.Repository;

import com.example.APIGiuaKy.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop10ByOrderByDateDesc();
}
//Lưu Xuân Dũng