package com.wxd.school.model;

import lombok.Data;

@Data
public class Message {
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

}
