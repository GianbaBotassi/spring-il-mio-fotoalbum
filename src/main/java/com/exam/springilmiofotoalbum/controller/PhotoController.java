package com.exam.springilmiofotoalbum.controller;

import com.exam.springilmiofotoalbum.exceptions.PhotoNotFoundException;
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

import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CategoryService categoryService;

    //Photo list with or without search word
    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        model.addAttribute("photos", photoService.getList(search));
        return "photos/list";
    }

    //Show details photo
    @GetMapping({"/show/{id}"})
    public String showDetails(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("photoDetail", photoService.getPhoto(id));
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "photos/show";
    }

    //Form with new Photo
    @GetMapping("/create")
    public String createPhoto(Model model) {
        model.addAttribute("photo", new Photo());
        model.addAttribute("categories", categoryService.getCategList());
        return "photos/form";
    }

    //store new photo
    @PostMapping("/create")
    public String storePhoto(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/photos/form";
        } else {
            Photo photoSaved = photoService.savePhoto(formPhoto);
            return "redirect:/photos/show/" + photoSaved.getId();
        }
    }

    //Form edit photo with data
    @GetMapping("/edit/{id}")
    public String editPhoto(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("photo", photoService.getPhoto(id));
            model.addAttribute("categories", categoryService.getCategList());
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "/photos/form";
    }

    //update photo
    @PostMapping("/edit/{id}")
    public String updatePhoto(@Valid @PathVariable Integer id, @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        formPhoto.setId(id);
        if (bindingResult.hasErrors()) {
            return "/photos/form";
        }
        photoService.editPhoto(formPhoto);
        return "redirect:/photos/show/" + formPhoto.getId();
    }

    //Delete photo
    @PostMapping("/delete/{id}")
    public String deletePhoto(@PathVariable Integer id) {
        try {
            photoService.deletePhoto(id);
            return "redirect:/photos";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
