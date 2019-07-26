package com.wxd.school.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<MessageDTO> messages;
    private boolean showPrePage;
    private boolean showFirPage;
    private boolean showNextPage;
    private boolean showEndPage;
    private Integer currentPage;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();


    public void setPage(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.currentPage = page;

        //显示页码跳转
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示第一页
        showFirPage = !pages.contains(1);
        //是否展示上一页
        showPrePage = page != 1;
        //是否展示下一页
        showNextPage = page != totalPage;
        //是否展示最后一页
        showEndPage = !pages.contains(totalPage);

    }
}
