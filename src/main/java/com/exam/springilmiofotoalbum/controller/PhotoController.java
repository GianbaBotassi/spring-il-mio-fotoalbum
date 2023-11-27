package com.exam.springilmiofotoalbum.controller;

import com.exam.springilmiofotoalbum.dto.PhotoDto;
import com.exam.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.model.User;
import com.exam.springilmiofotoalbum.repository.UserRepository;
import com.exam.springilmiofotoalbum.service.CategoryService;
import com.exam.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    //Photo list with or without search word
    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model, Authentication auth) {

        if (auth.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("SUPERADMIN"))) {
            model.addAttribute("photos", photoService.getList(search));
        } else {
            Optional<User> user = userRepository.findByEmail(auth.getName());

            model.addAttribute("photos", photoService.getListById(search, user.get().getId()));
        }
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
        model.addAttribute("photo", new PhotoDto());
        model.addAttribute("categories", categoryService.getCategList());
        return "photos/form";
    }

    //store new photo
    @PostMapping("/create")
    public String storePhoto(@Valid @ModelAttribute("photo") PhotoDto formPhoto, BindingResult bindingResult, Model model, Authentication auth) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getCategList());
            return "/photos/form";
        }
        try {
            Optional<User> user = userRepository.findByEmail(auth.getName());
            formPhoto.setUser(user.get());

            Photo photoSaved = photoService.savePhoto(formPhoto);
            return "redirect:/photos/show/" + photoSaved.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Form edit photo with data
    @GetMapping("/edit/{id}")
    public String editPhoto(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("photo", photoService.getPhotoDtoById(id));
            model.addAttribute("categories", categoryService.getCategList());
            return "/photos/form";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //update photo
    @PostMapping("/edit/{id}")
    public String updatePhoto(@Valid @PathVariable Integer id, @ModelAttribute("photo") PhotoDto formPhoto, BindingResult bindingResult, Model model) {
        formPhoto.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getCategList());
            return "/photos/form";
        }
        try {
            photoService.editPhoto(formPhoto);
            return "redirect:/photos/show/" + formPhoto.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Delete photo
    @PostMapping("/delete/{id}")
    public String deletePhoto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Photo photoMess = photoService.getPhoto(id);
            photoService.deletePhoto(id);
            redirectAttributes.addFlashAttribute("message", "Delete '" + photoMess.getTitle() + "' photo!");

            return "redirect:/photos";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
