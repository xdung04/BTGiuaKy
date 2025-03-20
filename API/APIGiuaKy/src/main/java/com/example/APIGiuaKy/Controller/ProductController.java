package com.example.APIGiuaKy.Controller;

import com.example.APIGiuaKy.Entity.Product;
import com.example.APIGiuaKy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<?> GetNewProduct(){
        return productService.findTop10ByOrderByDateDesc();
    }
}
//Lưu Xuân Dũng
