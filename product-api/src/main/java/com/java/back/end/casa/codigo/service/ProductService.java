package com.java.back.end.casa.codigo.service;

import com.java.back.end.casa.codigo.dto.DTOConverter;
import com.java.back.end.casa.codigo.repository.CategoryRepository;
import exception.CategoryNotFoundException;
import exception.ProductNotFoundException;
import com.java.back.end.casa.codigo.model.Product;
import com.java.back.end.casa.codigo.repository.ProductRepository;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null)
            return DTOConverter.convert(product);
        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO) {
        boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product product = productRepository
                .save(Product.convert(productDTO));
        return DTOConverter.convert(product);

    }

    public ProductDTO delete(long productId) throws ProductNotFoundException {
        Optional<Product> Product = productRepository.findById(productId);
        Product.ifPresent(product -> productRepository.delete(product));
        throw new ProductNotFoundException();
    }

}
