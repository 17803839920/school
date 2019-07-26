package com.wxd.school.controller;

import com.wxd.school.dto.PageDTO;
import com.wxd.school.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size) {

        PageDTO pagination =messageService.list(page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
