package com.plantsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plantsys.entity.User;
import com.plantsys.Vo.UserVo;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);

    /**
     * 用户注册
     * @param userVo
     * @return
     */
    void addRegister(UserVo userVo);
    /**
     * 重置密码
     * @param userid
     */
     void resetUserPwd(Integer userid);

    /**
     * 修改密码
     * @param userVo
     */
    void updateUserPwd(UserVo userVo);


}
