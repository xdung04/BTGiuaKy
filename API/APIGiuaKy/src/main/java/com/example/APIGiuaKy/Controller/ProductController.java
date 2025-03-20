package com.example.APIGiuaKy.Controller;

import com.example.APIGiuaKy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/lastproduct")
    public ResponseEntity<?> GetNewProduct(){
        return productService.findTop10ByOrderByDateDesc();
    }
}
//Lưu Xuân Dũng - 22110300