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

    //private methods to check users and admin
    private Optional<User> getAuthUser(Authentication auth) {
        Optional<User> user = userRepository.findByEmail(auth.getName());
        return user;
    }

    //check if user is present and if user id picture equals to user id
    private static boolean isUserAllowed(Optional<User> user, Photo photo) {
        return user.isPresent() && user.get().getId().equals(photo.getUser().getId());
    }

    //check if user has SUPERADMIN authority can see every photo
    private static boolean isSuperadmin(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("SUPERADMIN"));
    }

    //Check if one of methods is true
    private static boolean isSuperAdminOrAllowedUser(Authentication auth, Optional<User> user, Photo photo) {
        return isUserAllowed(user, photo) || isSuperadmin(auth);
    }


    //Photo list with or without search word
    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model, Authentication auth) {

        //Check if user has SUPERADMIN authority, if true show all photos
        if (isSuperadmin(auth)) {
            model.addAttribute("photos", photoService.getList(search));
        } else {
            //Else get photo list by user id
            Optional<User> user = getAuthUser(auth);
            model.addAttribute("photos", photoService.getListById(search, user.get().getId()));
        }
        return "photos/list";
    }


    //Show details photo
    @GetMapping({"/show/{id}"})
    public String showDetails(@PathVariable Integer id, Model model, Authentication auth) {

        //get user authenticate
        Optional<User> user = getAuthUser(auth);
        Photo photo = photoService.getPhoto(id);

        //Check if user has SUPERADMIN authority or user id has same photo.user.id
        if (isSuperAdminOrAllowedUser(auth, user, photo)) {
            try {
                model.addAttribute("photoDetail", photo);
            } catch (PhotoNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
            return "photos/show";
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }


    //Form with new Photo
    @GetMapping("/create")
    public String createPhoto(Model model) {

        //Add DTO photo
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
            //get user auth
            Optional<User> user = getAuthUser(auth);
            //Set id to new Photo from auth
            formPhoto.setUser(user.get());

            Photo photoSaved = photoService.savePhoto(formPhoto);
            return "redirect:/photos/show/" + photoSaved.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Form edit photo with data
    @GetMapping("/edit/{id}")
    public String editPhoto(@PathVariable Integer id, Model model, Authentication auth) {

        //get user auth
        Optional<User> user = getAuthUser(auth);
        Photo photo = photoService.getPhoto(id);

        //Check if user is allowed
        if (isUserAllowed(user, photo)) {

            try {
                model.addAttribute("photo", photoService.getPhotoDtoById(id));
                model.addAttribute("categories", categoryService.getCategList());
                return "/photos/form";
            } catch (PhotoNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
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
