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
    /**
     * 跳转到植物分类管理
     * @return
     */
    @RequestMapping("toSortManager")
    public String toSortManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "sort/sortManager";
    }
    /**
     * 跳转到科目分类管理
     * @return
     */
    @RequestMapping("toFamilyManager")
    public String toFamilyManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "sort/familyManager";
    }
    /**
     * 跳转到属目分类管理
     * @return
     */
    @RequestMapping("toGenusManager")
    public String toGenusManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "sort/genusManager";
    }
    /**
     * 跳转到种目分类管理
     * @return
     */
    @RequestMapping("toSpeciesManager")
    public String toSpeciesManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "sort/speciesManager";
    }
    /**
     * 跳转到省份分类管理
     * @return
     */
    @RequestMapping("toProvinceManager")
    public String toProvinceManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "sort/provinceManager";
    }
    /**
     * 跳转到市分类管理
     * @return
     */
    @RequestMapping("toCityManager")
    public String toCityManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "sort/cityManager";
    }
    /**
     * 跳转到县分类管理
     * @return
     */
    @RequestMapping("toCountyManager")
    public String toCountyManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "sort/countyManager";
    }
    /**
     * 跳转到患病植物列表
     * @return
     */
    @RequestMapping("toPlantDisease")
    public String toPlantDisease(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "treatment/plantDisease";
    }
    /**
     * 跳转到防治任务列表
     * @return
     */
    @RequestMapping("toTreatment")
    public String toTreatment(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getUserId());
        return "treatment/treatment";
    }
}
