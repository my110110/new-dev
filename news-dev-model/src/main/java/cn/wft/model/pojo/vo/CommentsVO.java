package cn.wft.model.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentsVO {

    private String commentId;
    private String fatherId;
    private String articleId;
    private String commentUserId;
    private String commentUserNickname;
    private String content;
    private Date createTime;
    private String quoteUserNickname;
    private String quoteContent;



}