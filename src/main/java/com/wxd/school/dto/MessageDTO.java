package com.wxd.school.dto;

import com.wxd.school.model.User;
import lombok.Data;

@Data
public class MessageDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creatorId;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private User user;
}
