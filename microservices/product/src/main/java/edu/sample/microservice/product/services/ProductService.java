package edu.sample.microservice.product.services;

import edu.sample.api.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductService {
    @GetMapping("/product/{productId}")
    public Product hi(@PathVariable("productId") Integer productId) {
        Product product = new Product();
        product.setProductId(productId);
        product.setName("masoud");
        return product;
    }
}
