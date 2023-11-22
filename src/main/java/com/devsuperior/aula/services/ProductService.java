package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        for (CategoryDTO cDTO : dto.getCategories()) {
            Category cat = new Category();
            cat.setId(cDTO.getId());
            entity.getCategories().add(cat);
        }
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    public ProductDTO insertModo2(ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        for (CategoryDTO cDTO : dto.getCategories()) {
            Category cat = categoryRepository.getReferenceById(cDTO.getId());
            entity.getCategories().add(cat);
        }
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }
}
