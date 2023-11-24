package com.exam.springilmiofotoalbum.controller;

import com.exam.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import com.exam.springilmiofotoalbum.exceptions.UniqueNameCategory;
import com.exam.springilmiofotoalbum.model.Category;
import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.service.CategoryService;
import com.exam.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getCategList());
        model.addAttribute("category", new Category());
        return "categories/list";
    }

    @PostMapping
    public String createCat(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryService.getCategList());
            return "categories/list";
        }
        try {
            categoryService.saveCateg(formCategory);
            return "redirect:/categories";
        } catch (UniqueNameCategory e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteCat(@PathVariable Integer id, Model model) {
        try {
            Category category = categoryService.getCateg(id);
            //Cycle list photos with category that want to remove
            for (Photo photo : category.getPhotos()) {
                //remove categ from each photo and then save
                photo.getCategories().remove(category);
                photoService.savePhoto(photo);
            }

            categoryService.deleteCateg(id);
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        model.addAttribute("categories", categoryService.getCategList());
        return "redirect:/categories";
    }
}
