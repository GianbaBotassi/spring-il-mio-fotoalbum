package com.exam.springilmiofotoalbum.repository;

import com.exam.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findByTitleContainingIgnoreCase(String keyword);

    List<Photo> findByUserIdAndTitleContainingIgnoreCase(Integer userId, String keyword);

    List<Photo> findByUserId(Integer id);
}
