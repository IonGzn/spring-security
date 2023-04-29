package com.pms.pmsapp.service;

import com.pms.pmsapp.dto.Product;
import com.pms.pmsapp.entity.User;
import com.pms.pmsapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ProductService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    List<Product> products;

    @PostConstruct
    public void getAllProductsFromDataBase() {
        products = IntStream.rangeClosed(1, 100)
                .mapToObj(p -> Product.builder()
                        .id(p)
                        .name("product " + p)
                        .price(new Random().nextInt(1000)).build()
                ).collect(Collectors.toList());
    }


    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException(id + " not found!"));
    }

    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "user added successfully...";
    }
}
