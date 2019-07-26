package com.wxd.school.controller;

import com.wxd.school.dto.PageDTO;
import com.wxd.school.mapper.UserMapper;
import com.wxd.school.model.User;
import com.wxd.school.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageService messageService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        switch (action) {
            case "publish":
                model.addAttribute("section", "publish");
                model.addAttribute("sectionName", "我的发布");
                break;
            case "myself":
                model.addAttribute("section", "myself");
                model.addAttribute("sectionName", "我的资料");
                break;
        }

        PageDTO pageDTO = messageService.list(user.getId(), page, size);
        model.addAttribute("pagination",pageDTO);
        return "profile";
    }
}
