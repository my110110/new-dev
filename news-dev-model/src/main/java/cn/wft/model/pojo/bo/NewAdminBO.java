package cn.wft.model.pojo.bo;

import lombok.Data;

/**
 * 添加管理人员的BO
 */

@Data
public class NewAdminBO {

    private String username;
    private String adminName;
    private String password;
    private String confirmPassword;
    private String img64;
    private String faceId;


    @Override
    public String toString() {
        return "NewAdminBO{" +
                "username='" + username + '\'' +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", img64='" + img64 + '\'' +
                ", faceId='" + faceId + '\'' +
                '}';
    }
}
