package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.*;
import com.plantsys.service.*;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("genus")
public class GenusController {
    @Autowired
    FamilyService familyService;
    @Autowired
    GenusInfoService genusInfoService;
    @Autowired
    GenusService genusService;
    @Autowired
    SpeciesService speciesService;


    // 添加属
    @RequestMapping("addGenus")
    public ResultObj addGenus(Genus genus) {
        QueryWrapper<Genus> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("genus_id",genus.getGenusId());
        int count=this.genusService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"属已存在");
        }
        try {
            genusService.save(genus);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    // 更新属
    @RequestMapping("updateGenus")
    public ResultObj updateGenus(Genus genus) {
        try {
            genusService.updateById(genus);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    @RequestMapping("genusList")
    public DataGridView genusList(QueryVo queryVo) {
        System.out.println("awfdawdaw");
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<GenusInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getGenusId(), "genus_id", queryVo.getGenusId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getGenusName()), "genus_name", queryVo.getGenusName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getFamilyName()), "family_name", queryVo.getFamilyName());
        List<GenusInfo> data = this.genusInfoService.list(queryWrapper);
        return new DataGridView(page.getTotal(), data);
    }

    // 删除属
    @RequestMapping("deleteGenus")
    public ResultObj deleteGenus(Integer genusId) {
        try {
            // 查询该属下的所有种
            QueryWrapper<Species> speciesWrapper = new QueryWrapper<>();
            speciesWrapper.eq("genus_id", genusId);
            List<Species> speciesList = speciesService.list(speciesWrapper);

            // 遍历删除种
            for (Species species : speciesList) {
                speciesService.removeById(species.getSpeciesId());
            }
            genusService.removeById(genusId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}


