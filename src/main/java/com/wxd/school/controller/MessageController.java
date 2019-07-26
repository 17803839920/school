package com.wxd.school.controller;

import com.wxd.school.dto.MessageDTO;
import com.wxd.school.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;


    @GetMapping("/message/{id}")
    public String showMessage(@PathVariable(name = "id") Integer id,
                              Model model){
        MessageDTO messageDTO = messageService.getById(id);
        model.addAttribute("message",messageDTO);
        return "message";
    }
}
