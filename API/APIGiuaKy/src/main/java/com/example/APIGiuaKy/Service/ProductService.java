package com.example.APIGiuaKy.Service;


import com.example.APIGiuaKy.DTO.ProductResponse;
import com.example.APIGiuaKy.Entity.Product;
import com.example.APIGiuaKy.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> findTop10ByOrderByDateDesc() {
        List<Product> listP = productRepository.findTop10ByOrderByDateDesc();

        Map<String, Object> response = new HashMap<>();
        if (listP.isEmpty()) {
            response.put("status", "fail");
            response.put("message", "Không có sản phẩm nào mới được thêm");
            return ResponseEntity.status(404).body(response);
        } else {
            // Map sang ProductResponse chỉ lấy avatar
            List<ProductResponse> data = listP.stream()
                    .map(p -> new ProductResponse(p.getAvatar()))
                    .toList();

            response.put("status", "success");
            response.put("data", data);
            return ResponseEntity.ok(response);
        }
    }

}
//Lưu Xuân Dũng - 22110300
