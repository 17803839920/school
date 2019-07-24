package com.wxd.school.controller;

import com.wxd.school.mapper.MessageMapper;
import com.wxd.school.mapper.UserMapper;
import com.wxd.school.model.Message;
import com.wxd.school.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publist(){
        return "publish";
    }

    @PostMapping("/publish")
    public String create(@RequestParam("title") String title,
                         @RequestParam("description") String description,
                         @RequestParam("tag") String tag,
                         HttpServletRequest request,
                         Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title ==null || title.equals("")){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(description ==null || description.equals("")){
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if(tag ==null || tag.equals("")){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.select(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Message message = new Message();
        message.setTitle(title);
        message.setDescription(description);
        message.setTag(tag);
        message.setCreatorId(user.getId());
        message.setGmtCreate(System.currentTimeMillis());
        message.setGmtModified(message.getGmtCreate());
        messageMapper.create(message);
        return "redirect:/";
    }
}
