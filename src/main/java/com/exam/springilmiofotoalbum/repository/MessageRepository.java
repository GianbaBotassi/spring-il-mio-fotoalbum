package com.exam.springilmiofotoalbum.repository;

import com.exam.springilmiofotoalbum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByOrderByReceivedDesc();
}
