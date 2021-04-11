package cn.wft.user.mapper;

import cn.wft.api.my.MyMapper;
import cn.wft.model.pojo.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
public interface AppUserMapper extends MyMapper<AppUser> {
}