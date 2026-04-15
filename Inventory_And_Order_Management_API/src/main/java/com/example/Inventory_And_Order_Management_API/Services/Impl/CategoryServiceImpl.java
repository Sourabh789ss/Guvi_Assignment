package com.example.Inventory_And_Order_Management_API.Services.Impl;

import com.example.Inventory_And_Order_Management_API.Entity.Category;
import com.example.Inventory_And_Order_Management_API.Repository.CategoryRepository;
import com.example.Inventory_And_Order_Management_API.Services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}