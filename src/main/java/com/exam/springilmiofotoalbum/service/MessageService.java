package com.exam.springilmiofotoalbum.service;

import com.exam.springilmiofotoalbum.model.Message;
import com.exam.springilmiofotoalbum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getListMessages() {
        return messageRepository.findAllByOrderByReceivedDesc();
    }

    public Message saveMessage(Message mess) {
        return messageRepository.save(mess);
    }
}
