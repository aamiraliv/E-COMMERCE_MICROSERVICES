package com.microservice.product_service.service;

import com.microservice.product_service.dto.ProductDTO;
import com.microservice.product_service.exception.ProductNotFoundException;
import com.microservice.product_service.model.Product;
import com.microservice.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MapperService mapperService;

    public List<ProductDTO> getAllProducts() {
        return repository.findAll().stream().map(mapperService::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("item not found with id : "+ id));
        return mapperService.convertToDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapperService.toEntity(productDTO);
        product = repository.save(product);
        return mapperService.convertToDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product prevproduct = repository.findById(id).orElseThrow(()->new ProductNotFoundException("item not found With id :"+ id) {
        });

        Product updatedProd = mapperService.toEntity(productDTO);

        prevproduct.setName(updatedProd.getName());
        prevproduct.setCategory(updatedProd.getCategory());
        prevproduct.setPrice(updatedProd.getPrice());
        prevproduct.setDescription(updatedProd.getDescription());
        prevproduct.setImage_url(updatedProd.getImage_url());

        Product savedProd = repository.save(prevproduct);
        return mapperService.convertToDTO(savedProd);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

}
