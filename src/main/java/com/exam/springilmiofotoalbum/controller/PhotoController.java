package com.exam.springilmiofotoalbum.controller;

import com.exam.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        model.addAttribute("photos", photoService.getList(search));
        return "photos/list";
    }

    @GetMapping({"/show/{id}"})
    public String showDetails(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("photoDetail", photoService.getPhotoDetail(id));
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "photos/show";
    }

    @GetMapping("/create")
    public String createPhoto(Model model) {
        model.addAttribute("photo", new Photo());
        return "photos/form";
    }

    @PostMapping("/create")
    public String storePhoto(@ModelAttribute("photo") Photo formPhoto) {
        Photo photoSaved = photoService.savePhoto(formPhoto);
        return "redirect:/photos/show/" + photoSaved.getId();
    }

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
