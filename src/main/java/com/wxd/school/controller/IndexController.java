package com.wxd.school.controller;

import com.wxd.school.dto.MessageDTO;
import com.wxd.school.mapper.MessageMapper;
import com.wxd.school.mapper.UserMapper;
import com.wxd.school.model.Message;
import com.wxd.school.model.User;
import com.wxd.school.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.select(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<MessageDTO> messageList =messageService.list();
        model.addAttribute("messages", messageList);
        return "index";
    }
}
