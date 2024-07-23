package com.vheekey.crud.product.services;

import com.vheekey.crud.common.errors.NotFoundException;
import com.vheekey.crud.employee.entities.EmployeeEntity;
import com.vheekey.crud.employee.repositories.EmployeeRepository;
import com.vheekey.crud.product.entities.ProductEntity;
import com.vheekey.crud.product.interfaces.ProductServiceInterface;
import com.vheekey.crud.product.models.Product;
import com.vheekey.crud.product.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          EmployeeRepository employeeRepository
    ) {
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Product createNewProduct(Product product) {
        ProductEntity productEntity = this.productRepository.save(toProductEntity(product));

        return toModelDTO(productEntity);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        // Retrieve the entity from the repository
        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(id);

        // Use Optional's map to convert the entity to a DTO if present
        return optionalProductEntity.map(this::toModelDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository
                .findAll()
                .stream()
                .map(productEntity -> toModelDTO(productEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Product editProduct(Product product, long id) {
        Optional<ProductEntity> foundProductEntity = this.productRepository.findById(id);

        if (!foundProductEntity.isPresent()) {
            throw new NotFoundException("Product with id " + id + " not found");
        }

        ProductEntity existingProductEntity = foundProductEntity.get();

        if (product.name() != null) {
            existingProductEntity.setName(product.name());
        }
        if (product.tag() != null) {
            existingProductEntity.setTag(product.tag());
        }
        if (product.price() != null) {
            existingProductEntity.setPrice(product.price());
        }
        if (product.isAvailable()) {
            existingProductEntity.setAvailable(product.isAvailable());
        }
        if (product.createdBy() != null) {
            Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(product.createdBy());

            if (!employeeEntity.isPresent()) {
                throw new NotFoundException("Employee not found");
            } else {
                existingProductEntity.setEmployeeEntity(employeeEntity.get());
            }
        }

        this.productRepository.save(existingProductEntity);

        return toModelDTO(existingProductEntity);
    }

    @Override
    public void removeProductById(Long id) {
        if (!this.productRepository.existsById(id)) {
            throw new NotFoundException(" Operation Failed! Product not found ");
        }
        this.productRepository.deleteById(id);
    }

    private Product toModelDTO(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getTag(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.isAvailable(),
                productEntity.getEmployeeEntity().getId()
        );
    }

    private ProductEntity toProductEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        EmployeeEntity employeeEntity = new EmployeeEntity();

        BeanUtils.copyProperties(product, productEntity);
        employeeEntity.setId(product.createdBy());
        productEntity.setEmployeeEntity(employeeEntity);

        return productEntity;
    }
}
