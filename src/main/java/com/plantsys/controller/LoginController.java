package com.plantsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plantsys.constant.SysConstant;
import com.plantsys.entity.User;
import com.plantsys.service.UserService;
import com.plantsys.util.WebUtils;
import com.plantsys.Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * @return 跳转到登录页面的方法
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "main/login";
    }

    /**
     * @return 跳转到注册页面的方法
     */
    @RequestMapping("toRegister")
    public String toRegister() {
        return "main/register";
    }

    /**
     * @return 跳转到重置密码页面的方法
     */
    @RequestMapping("toRestPwd")
    public String toRestPwd() {
        return "main/restPwd";
    }

    /**
     * .登陆方法
     *
     * @param userVo
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(UserVo userVo, Model model) {
        try {
            if(userVo.getRid() == null) {
                model.addAttribute("error", SysConstant.USER_LOGIN_CODE_ERROR_MSG);
                return "main/login";
            }
            System.out.println(userVo.getLoginName());
            System.out.println(userVo.getPassword());
            System.out.println(userVo.getRid());

            User user = this.userService.login(userVo);
            System.out.println("user = " + user);
            if (null != user) {
                //放入到session域中
                WebUtils.getHttpSession().setAttribute("user", user);
                return "main/index";
            } else {
                model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
                return "main/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
            return "main/login";
        }
    }

    /**
     * 注册
     * @param userVo
     * @param model
     * @return
     */
    @RequestMapping("register")
    public String register(UserVo userVo, Model model) {
        // 判断用户名是否唯一
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("loginname",userVo.getLoginName());
        int count=this.userService.count(wrapper);
        if(count > 0) {
            model.addAttribute("error", SysConstant.USER_REGISTER_CODE_ERROR_MSG);
            return "main/register";
        }
        userVo.setRid(3);
        this.userService.addRegister(userVo);
        model.addAttribute("error", SysConstant.USER_REGISTER_CODE_SUCCESS_MSG);
        return "main/login";
    }




}
