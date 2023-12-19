package com.plantsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plantsys.constant.SysConstant;
import com.plantsys.entity.User;
import com.plantsys.service.UserService;
import com.plantsys.util.WebUtils;
import com.plantsys.Vo.UserVo;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

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
    public String login(UserVo userVo, @RequestParam String captcha,Model model) {
        // 从 Session 中获取之前存储的验证码
        String sessionCaptcha = (String) WebUtils.getHttpSession().getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equals(captcha)) {
            model.addAttribute("error","验证码错误");
            return "main/login";
        }
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
                WebUtils.getHttpSession().setAttribute("role",user.getRid());
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

    @RequestMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response)throws IOException {
        // 生成验证码
        Captcha captcha = new Captcha.Builder(150, 50)
                .addNoise(new CurvedLineNoiseProducer())
                .addText()
                .addBackground(new FlatColorBackgroundProducer(Color.CYAN))  // 添加背景
                .build();
        // 将验证码存储在Session中
        request.getSession().setAttribute("captcha", captcha.getAnswer());
        // 设置响应类型为图片 返回验证码图片
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        // 输出验证码图片
        ImageIO.write(captcha.getImage(), "png", os);
        os.close();
    }


}
