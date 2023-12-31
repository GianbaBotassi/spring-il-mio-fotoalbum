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

    //get List with or without search keyword
    public List<Photo> getList(Optional<String> search) {
        if (search.isPresent()) {
            return photoRepository.findByTitleContainingIgnoreCase(search.get());
        }
        return photoRepository.findAll();
    }

    //Get List from ID with or without search keyword
    public List<Photo> getListById(Optional<String> search, Integer id) {
        if (search.isPresent()) {
            return photoRepository.findByUserIdAndTitleContainingIgnoreCase(id, search.get());
        }
        return photoRepository.findByUserId(id);
    }

    //Get photo by id with exception
    public Photo getPhoto(Integer id) {
        return photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo not found"));
    }

    //Save photo
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    //// Save the Photo entity after it has been converted from the DTO
    public Photo savePhoto(PhotoDto photoDto) throws IOException {
        Photo photoToSave = convertDtoToPhoto(photoDto);
        return savePhoto(photoToSave);
    }

    //Edit photo
    public Photo editPhoto(Photo formPhoto) {
        Photo photoToEdit = getPhoto(formPhoto.getId());
        photoToEdit.setTitle(formPhoto.getTitle());
        photoToEdit.setDescription(formPhoto.getDescription());
        photoToEdit.setPicture(formPhoto.getPicture());
        photoToEdit.setVisible(formPhoto.isVisible());
        photoToEdit.setCategories(formPhoto.getCategories());

        return savePhoto(photoToEdit);
    }

    //Edit photo with DTO argument
    public Photo editPhoto(PhotoDto formPhotoDto) throws IOException {
        Photo photo = convertDtoToPhoto(formPhotoDto);
        return editPhoto(photo);
    }

    //Private method to convert DTO to photo
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
        if (photoDto.getUser() != null) {
            photoToSave.setUser(photoDto.getUser());
        }
        return photoToSave;
    }

    //Get DTO photo by id
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

    //Delete photo
    public void deletePhoto(Integer id) {
        Photo photoToDelete = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo not found"));
        photoRepository.delete(photoToDelete);
    }
}
