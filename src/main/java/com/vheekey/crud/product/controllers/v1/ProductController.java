package com.vheekey.crud.product.controllers.v1;

import com.vheekey.crud.common.errors.NotFoundException;
import com.vheekey.crud.product.models.Product;
import com.vheekey.crud.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController("ProductControllerV1")
public class ProductController {

    private final AtomicLong counter = new AtomicLong();
    private static final String defTag = "PR-%s";

    @Autowired
    private ProductService productService;

    @GetMapping("/v1/products/get")
    public Product product(@RequestParam(defaultValue = "1001", required = false) String tag) {
        return new Product(
                counter.incrementAndGet(),
                String.format(defTag, tag),
                "Product Name",
                20.50,
                true,
                1

        );
    }

    @PostMapping("/v1/products/create")
    public Product createDemo(@RequestBody Product product) {
        long id = counter.incrementAndGet();
        return new Product(
                id,
                product.tag(),
                product.name(),
                product.price(),
                product.isAvailable(),
                product.createdBy()
        );
    }

    @PutMapping("/v1/products/edit/{id}")
    public ResponseEntity<Product> updateDemo(@PathVariable long id, @RequestBody Product product) {
        Product updatedProduct = new Product(
                id,
                product.tag(),
                product.name(),
                product.price(),
                product.isAvailable(),
                product.createdBy()
        );

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/v1/products/delete/{id}")
    public ResponseEntity<Product> deleteDemo(@PathVariable long id, @RequestBody Product product){
        productService.removeProductById(id);
        return ResponseEntity.noContent().build();
    }
}
