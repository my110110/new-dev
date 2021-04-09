package cn.wft.model.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDetailVO {

    private String id;
    private String title;
    private String cover;
    private Integer categoryId;
    private String categoryName;
    private String publishUserId;
    private Date publishTime;
    private String content;

    private String publishUserName;
    private Integer readCounts;


}