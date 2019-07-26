package com.wxd.school.service;

import com.wxd.school.dto.MessageDTO;
import com.wxd.school.dto.PageDTO;
import com.wxd.school.mapper.MessageMapper;
import com.wxd.school.mapper.UserMapper;
import com.wxd.school.model.Message;
import com.wxd.school.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过messageMapper.list()查询messages列表，遍历messages列表
     * 通过每个message的CreatorId找到user
     * 将message  copy给messageDTO对象，再将user对象添加 到messageDTO
     * 最后获取messageDTO列表
     * 找出所有的消息
     *
     * @param page
     * @param size
     * @return
     */
    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        Integer totalCount = messageMapper.count();

        //判断一共有多少页
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }

        pageDTO.setPage(totalPage, page);
        //size*(page-1)
        Integer start = size * (page - 1);
        List<Message> messages = messageMapper.list(start, size);
        List<MessageDTO> messageDTOList = new ArrayList<>();

        for (Message message : messages) {
            User user = userMapper.findById(message.getCreatorId());
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            messageDTO.setUser(user);
            messageDTOList.add(messageDTO);
        }
        pageDTO.setMessages(messageDTOList);
        return pageDTO;
    }


    /**
     * 根据userId找出该用户发布的消息
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PageDTO list(Integer userId, Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer totalPage;
        Integer totalCount = messageMapper.countByUserId(userId);
        //判断一共有多少页
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }
        pageDTO.setPage(totalPage, page);
        //size*(page-1)
        Integer start = size * (page - 1);
        List<Message> messages = messageMapper.listByUserId(userId, start, size);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (Message message : messages) {
            User user = userMapper.findById(message.getCreatorId());
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            messageDTO.setUser(user);
            messageDTOList.add(messageDTO);
        }
        pageDTO.setMessages(messageDTOList);
        return pageDTO;
    }

    /**
     * 根据messageID查询一条message的详细信息
     * @param id
     * @return
     */
    public MessageDTO getById(Integer id) {
        Message message = messageMapper.getById(id);
        MessageDTO messageDTO= new MessageDTO();
        BeanUtils.copyProperties(message,messageDTO);
        User user = userMapper.findById(message.getCreatorId());
        messageDTO.setUser(user);
        return messageDTO;
    }

    public void createOrUpdate(Message message) {
        if(message.getId() == null){
            //创建
            message.setGmtCreate(System.currentTimeMillis());
            message.setGmtModified(message.getGmtCreate());
            messageMapper.create(message);
        }else {
            //更新
            message.setGmtModified(message.getGmtCreate());
            messageMapper.update(message);
        }
    }

}
