package com.plantsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.constant.SysConstant;
import com.plantsys.entity.User;
import com.plantsys.mapper.UserMapper;
import com.plantsys.service.UserService;
import com.plantsys.Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    private UserVo userVo;

    @Override
    public User login(UserVo userVo) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("login_name", userVo.getLoginName());
            //明文生成密文
//            queryWrapper.eq("pwd", DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes()));
            queryWrapper.eq("password", userVo.getPassword());
            queryWrapper.eq(null != userVo.getRid(), "rid", userVo.getRid());
            return userMapper.selectOne(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addRegister(UserVo userVo) {
        //设置密码
        userVo.setPassword(userVo.getPassword());
        this.userMapper.insert(userVo);
    }

    /**
     * 重置用户密码
     * @param userid
     */
    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserId(userid);
        //设置默认密码
        user.setPassword(SysConstant.USER_DEFAULT_PWD);
        //设置完成后更新
        this.userMapper.updateById(user);
    }

    /**
     * 更新密码
     * @param userVo
     */
    @Override
    public void updateUserPwd(UserVo userVo) {
        this.userVo = userVo;
        User user = new User();
        user.setUserId(userVo.getUserId());
        //设置密码
        user.setPassword(userVo.getPassword());
        //设置完成后更新
        this.userMapper.updateById(user);
    }
}
