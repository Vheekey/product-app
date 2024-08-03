package com.vheekey.crud.product.controllers.v2;

import com.vheekey.crud.common.errors.NotFoundException;
import com.vheekey.crud.product.models.Product;
import com.vheekey.crud.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ProductControllerV2")
@RequestMapping("/v2/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product newProduct = this.productService.createNewProduct(product);

        return ResponseEntity.ok(newProduct);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable long id) {
        return this.productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Product with id: " + id + " not found "));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @Valid @RequestBody Product product) {
        Product updatedProduct = this.productService.editProduct(product, id);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        this.productService.removeProductById(id);

        return ResponseEntity.noContent().build();
    }
}
