package com.pms.pmsapp.controller;

import com.pms.pmsapp.dto.Product;
import com.pms.pmsapp.entity.User;
import com.pms.pmsapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/home")
    public String home() {
        return "homepage";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        return productService.addUser(user);
    }

    @GetMapping("/getProductById/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/getAllProducts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }
}
