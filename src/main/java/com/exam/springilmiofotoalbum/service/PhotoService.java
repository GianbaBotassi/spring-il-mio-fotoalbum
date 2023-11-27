package com.exam.springilmiofotoalbum.service;

import com.exam.springilmiofotoalbum.dto.PhotoDto;
import com.exam.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.exam.springilmiofotoalbum.model.Photo;
import com.exam.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public Photo getPhoto(Integer id) {
        return photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo not found"));
    }

    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo savePhoto(PhotoDto photoDto) throws IOException {
        Photo photoToSave = convertDtoToPhoto(photoDto);
        return savePhoto(photoToSave);
    }


    public Photo editPhoto(Photo formPhoto) {
        Photo photoToEdit = getPhoto(formPhoto.getId());
        photoToEdit.setTitle(formPhoto.getTitle());
        photoToEdit.setDescription(formPhoto.getDescription());
        photoToEdit.setPicture(formPhoto.getPicture());
        photoToEdit.setVisible(formPhoto.isVisible());
        photoToEdit.setCategories(formPhoto.getCategories());

        return savePhoto(photoToEdit);
    }

    public Photo editPhoto(PhotoDto formPhotoDto) throws IOException {
        Photo photo = convertDtoToPhoto(formPhotoDto);
        return editPhoto(photo);
    }

    public void deletePhoto(Integer id) {
        Photo photoToDelete = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo not found"));
        photoRepository.delete(photoToDelete);
    }

    private static Photo convertDtoToPhoto(PhotoDto photoDto) throws IOException {
        Photo photoToSave = new Photo();
        photoToSave.setId(photoDto.getId());
        photoToSave.setTitle(photoDto.getTitle());
        photoToSave.setDescription(photoDto.getDescription());
        photoToSave.setVisible(photoDto.isVisible());
        photoToSave.setCategories(photoDto.getCategories());
        if (photoDto.getPicture() != null && !photoDto.getPicture().isEmpty()) {
            byte[] bytes = photoDto.getPicture().getBytes();
            photoToSave.setPicture(bytes);
        }
        return photoToSave;
    }

    public PhotoDto getPhotoDtoById(Integer id) throws PhotoNotFoundException {
        Photo photo = getPhoto(id);
        return convertPhotoToDto(photo);
    }

    private static PhotoDto convertPhotoToDto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(photo.getId());
        photoDto.setTitle(photo.getTitle());
        photoDto.setDescription(photo.getDescription());
        photoDto.setVisible(photo.isVisible());
        photoDto.setCategories(photo.getCategories());
        photoDto.setId(photo.getId());

        return photoDto;
    }
}
