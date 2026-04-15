package com.example.Inventory_And_Order_Management_API.Services;

import com.example.Inventory_And_Order_Management_API.Entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    List<Category> getAll();
}