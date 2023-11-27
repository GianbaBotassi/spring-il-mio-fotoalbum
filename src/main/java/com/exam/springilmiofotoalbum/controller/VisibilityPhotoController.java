package com.exam.springilmiofotoalbum.controller;

import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visib")
public class VisibilityPhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/{id}")
    public String getVis(@PathVariable Integer id) {
        Photo photo = photoService.getPhoto(id);
        photo.setVisible(!photo.isVisible());
        photoService.savePhoto(photo);
        return "redirect:/photos/show/" + photo.getId();
    }
}
