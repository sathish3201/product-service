package com.sthitha.microservices.product.service;


import com.sthitha.microservices.product.dto.ProductRequest;
import com.sthitha.microservices.product.dto.ProductResponse;
import com.sthitha.microservices.product.model.Product;
import com.sthitha.microservices.product.repository.ProductRepository;
import com.sthitha.microservices.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
//        log.info("product from react : {}",productRequest.toString());
        Product product =Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product {} is created successfully",product.getId());
        return mapToProductResponse(product);
    }

 public String removeProductById(String id){

     productRepository.deleteById(id);
     return "Sucessfully removed";
 }

    public List<ProductResponse> getAllProducts() {
        RemoveALlProductwithNull();
        return productRepository.findAll()
                .stream()
                .filter((product -> product.getName() != null && product.getDescription() != null && product.getPrice() != null))
                .map(this::mapToProductResponse)
                .toList();
    }


    private  void RemoveALlProductwithNull(){
        productRepository.findAll()
                .stream()
                .filter(product -> product.getName() ==null || product.getDescription() ==null || product.getPrice() ==null)
                .forEach(productRepository::delete);
    }
    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
