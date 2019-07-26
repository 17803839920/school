package com.wxd.school.controller;

import com.wxd.school.dto.MessageDTO;
import com.wxd.school.model.Message;
import com.wxd.school.model.User;
import com.wxd.school.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        MessageDTO message = messageService.getById(id);
        model.addAttribute("title",message.getTitle());
        model.addAttribute("description",message.getDescription());
        model.addAttribute("tag",message.getTag());
        model.addAttribute("id",id);
        return "publish";
    }

    @GetMapping("/publish")
    public String publist(){
        return "publish";
    }

    @PostMapping("/publish")
    public String create(@RequestParam(value = "title", required = false) String title,
                         @RequestParam(value ="description", required = false) String description,
                         @RequestParam(value ="tag",required = false) String tag,
                         @RequestParam(value = "id",required = false) Integer id,
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

        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Message message = new Message();
        message.setTitle(title);
        message.setDescription(description);
        message.setTag(tag);
        message.setCreatorId(user.getId());
        message.setId(id);
        messageService.createOrUpdate(message);
        return "redirect:/";
    }
}
