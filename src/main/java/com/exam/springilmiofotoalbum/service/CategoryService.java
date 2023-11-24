package com.exam.springilmiofotoalbum.service;

import com.exam.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.exam.springilmiofotoalbum.exceptions.UniqueNameCategory;
import com.exam.springilmiofotoalbum.model.Category;
import com.exam.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategList() {
        return categoryRepository.findAll();
    }

    public Category getCateg(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Category not found"));
    }

    public Category saveCateg(Category ingrediente) {

        if (categoryRepository.existsByName(ingrediente.getName())) {
            throw new UniqueNameCategory("Category already exists");
        }
        return categoryRepository.save(ingrediente);
    }

    public void deleteCateg(Integer id) {
        categoryRepository.deleteById(id);
    }
}
