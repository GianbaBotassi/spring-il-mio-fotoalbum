package com.exam.springilmiofotoalbum.repository;

import com.exam.springilmiofotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String keyword);
}
