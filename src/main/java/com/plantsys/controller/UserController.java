package com.plantsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plantsys.entity.User;
import com.plantsys.service.UserService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import com.plantsys.Vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 加载用户列表返回DataGridView
     * @param userVo
     * @return
     */
    @RequestMapping("findPage")
    public DataGridView findPage(UserVo userVo){
        Page<User> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> data = this.userService.list(queryWrapper);

        // 去掉管理员
        if (data != null && data.get(0).getRid() == 1) {
            data.remove(0);
        }
        return new DataGridView(page.getTotal(),data);
    }
    /**
     * 添加或修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("save")
    public ResultObj save(UserVo userVo){
        try {
            System.out.println("userVo.getId():"+userVo.getUserId());
            if (null == userVo.getUserId()) {
                // 密码为工号（登录名称）后四位
                if (userVo.getLoginName().length() >= 4) {
                    String pwd = userVo.getLoginName().substring(userVo.getLoginName().length() - 4);
                    System.out.println("pwd:"+pwd);
                    userVo.setPassword(pwd);
                } else {
                    userVo.setPassword(userVo.getLoginName());
                }

            }

            // 编号唯一
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("login_name", userVo.getLoginName());
            if (null != userVo.getUserId()) {
                wrapper.ne("user_id", userVo.getUserId());
            }
            int count = this.userService.count(wrapper);
            if (count > 0) {
                return new ResultObj(-1, "用户名已存在");
            }
//            User user=this.userService.getById(userVo.getId());
            userVo.setRid(2);
            this.userService.saveOrUpdate(userVo);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo){

        try{
            // 加上ID
            User user=(User) WebUtils.getHttpSession().getAttribute("user");
            userVo.setUserId(user.getUserId());

            this.userService.saveOrUpdate(userVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除用户
     * @param userVo
     * @return
     */
    @RequestMapping("delete")
    public ResultObj delete(UserVo userVo){
        try {
            this.userService.removeById(userVo.getUserId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置密码
     * @param userVo
     * @return
     */
    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(UserVo userVo){
        try {
            this.userService.resetUserPwd(userVo.getUserId());
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 修改密码
     * @param userVo
     * @return
     */
    @RequestMapping("updateUserPwd")
    public ResultObj updateUserPwd(UserVo userVo){
        try {
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            userVo.setUserId(user.getUserId());
            this.userService.updateUserPwd(userVo);
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("getUser")
    public DataGridView getUser(){
        try {
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            user = this.userService.getById(user.getUserId());
            return new DataGridView(user);
        }catch (Exception e){
            e.printStackTrace();
            return new DataGridView();
        }
    }
}
