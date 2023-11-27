package com.exam.springilmiofotoalbum.restApi;

import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class RestPhotoController {
    @Autowired
    private PhotoService photoService;

    //Show every photo
    @GetMapping
    public List<Photo> getPizzasList(@RequestParam Optional<String> search) {
        return photoService.getList(search);
    }
}
