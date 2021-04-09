package cn.wft.model.pojo.vo;

import lombok.Data;

@Data
public class AppUserVO {
    private String id;
    private String nickname;
    private String face;
    private Integer activeStatus;

    private Integer myFollowCounts;
    private Integer myFansCounts;


    @Override
    public String toString() {
        return "AppUserVO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", face='" + face + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}