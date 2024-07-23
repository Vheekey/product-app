package com.vheekey.crud.product.interfaces;

import com.vheekey.crud.product.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {

    Product createNewProduct(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> getAllProducts();

    Product editProduct(Product product, long id);

    void removeProductById(Long id);
}
