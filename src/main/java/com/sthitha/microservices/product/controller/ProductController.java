package com.sthitha.microservices.product.controller;

import com.sthitha.microservices.product.dto.ProductRequest;
import com.sthitha.microservices.product.dto.ProductResponse;
import com.sthitha.microservices.product.model.Product;
import com.sthitha.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        log.info("product from react : {}",productRequest.toString());
        return  productService.createProduct(productRequest);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String removeProduct(@RequestParam String id){
        return productService.removeProductById(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
