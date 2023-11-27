package com.exam.springilmiofotoalbum.dto;

import com.exam.springilmiofotoalbum.model.Category;
import com.exam.springilmiofotoalbum.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class PhotoDto {


    private Integer id;
    @NotBlank(message = "Field title cannot be empty")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;
    @NotBlank(message = "Field description cannot be empty")
    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    private MultipartFile picture;
    private boolean visible;

    private User user;

    private List<Category> categories = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
