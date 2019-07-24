package com.wxd.school.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;//用户简介
    private String avatarUrl;

}
