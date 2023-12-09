package com.plantsys.controller;


import com.plantsys.entity.User;

import com.plantsys.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("sys")
public class SysController {


    /**
     * 跳转到菜单管理的右边的菜单树页面
     * @return
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "menu/MenuLeft";
    }


    /**
     * 跳转到后台首页的页面
     * @return
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "main/deskManger";
    }

    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "user/userManager";
    }


    /**
     * 跳转到植物信息管理
     * @return
     */
    @RequestMapping("toPlantManager")
    public String toBookManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "plant/plantManager";
    }


}
