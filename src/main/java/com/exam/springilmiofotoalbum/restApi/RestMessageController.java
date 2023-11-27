package com.exam.springilmiofotoalbum.restApi;

import com.exam.springilmiofotoalbum.model.Message;
import com.exam.springilmiofotoalbum.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/messages")
public class RestMessageController {

    @Autowired
    private MessageService messageService;

    //Method to receive messages from FE
    @PostMapping
    public Message storeMessage(@Valid @RequestBody Message objMessage) {
        return messageService.saveMessage(objMessage);
    }
}
