package com.exam.springilmiofotoalbum.service;

import com.exam.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getList(Optional<String> search) {
        if (search.isPresent()) {
            return photoRepository.findByTitleContainingIgnoreCase(search.get());
        }
        return photoRepository.findAll();
    }

    public Photo getPhotoDetail(Integer id) {
        return photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo not found"));
    }
}
