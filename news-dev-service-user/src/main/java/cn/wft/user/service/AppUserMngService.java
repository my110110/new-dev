package cn.wft.user.service;


import cn.wft.common.utils.PagedGridResult;

import java.util.Date;

public interface AppUserMngService {

    /**
     * 查询管理员列表
     */
     PagedGridResult queryAllUserList(String nickname,
                                            Integer status,
                                            String startDate,
                                            String endDate,
                                            Integer page,
                                            Integer pageSize);

    /**
     * 冻结用户账号，或者解除冻结状态
     */
     void freezeUserOrNot(String userId, Integer doStatus);

}
