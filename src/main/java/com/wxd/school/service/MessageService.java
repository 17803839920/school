package com.wxd.school.service;

import com.wxd.school.dto.MessageDTO;
import com.wxd.school.mapper.MessageMapper;
import com.wxd.school.mapper.UserMapper;
import com.wxd.school.model.Message;
import com.wxd.school.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    public List<MessageDTO> list() {
        List<Message> messages = messageMapper.list();
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (Message message : messages) {
            User user = userMapper.findById(message.getCreatorId());
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message,messageDTO);
            messageDTO.setUser(user);
            messageDTOList.add(messageDTO);
        }
        return messageDTOList;
    }
}
