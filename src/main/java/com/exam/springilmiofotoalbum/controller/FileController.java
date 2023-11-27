package com.exam.springilmiofotoalbum.controller;

import com.exam.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/picture/{id}")
    public ResponseEntity<byte[]> servePicture(@PathVariable Integer id) {
        try {
            Photo photo = photoService.getPhoto(id);
            byte[] pictureBytes = photo.getPicture();
            if (pictureBytes != null && pictureBytes.length > 0) {
                MediaType mediaType = MediaType.IMAGE_JPEG;
                return ResponseEntity.ok().contentType(mediaType).body(pictureBytes);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (PhotoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
