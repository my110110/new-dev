package cn.wft.admin.service;

import cn.wft.common.utils.PagedGridResult;
import cn.wft.model.pojo.AdminUser;
import cn.wft.model.pojo.bo.NewAdminBO;

public interface AdminUserService {

    /**
     * 获得管理员的用户信息
     */
    public AdminUser queryAdminByUsername(String username);

    /**
     * 新增管理员
     */
    public void createAdminUser(NewAdminBO newAdminBO);

    /**
     * 分页查询admin列表
     */
    public PagedGridResult queryAdminList(Integer page, Integer pageSize);

}
