package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.SortInfo;
import com.plantsys.entity.SpeciesCountyInfo;
import com.plantsys.service.*;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sortInfo")
public class SortController {
    // 添加分类信息
    @Autowired
    private FamilyService familyService;
    @Autowired
    private GenusService genusService;
    @Autowired
    private SpeciesService speciesService;
    @Autowired
    private SpeciesCountyInfoService sortInfoService;
    @PostMapping("addSortInfo")
    public ResultObj addSortInfo(SpeciesCountyInfo sortInfo) {
        QueryWrapper<SpeciesCountyInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("plant_id",sortInfo.getSpeciesId());
        int count=this.sortInfoService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"植物已存在");
        }
        try {
            sortInfoService.save(sortInfo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    // 修改分类信息
    @RequestMapping("updateSortInfo")
    public ResultObj updateSortInfo(SpeciesCountyInfo sortInfo) {
        try {
            sortInfoService.updateById(sortInfo);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    // 删除分类信息
    @RequestMapping("deleteSortInfo")
    public ResultObj deleteSortInfo(Integer plantId) {
        try {
            sortInfoService.removeById(plantId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    // 查询分类信息列表
    @RequestMapping("sortInfoList")
    public DataGridView sortInfoList(QueryVo queryVo) {
        System.out.println("awfdawdaw");
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<SpeciesCountyInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getSpeciesId(), "species_id", queryVo.getSpeciesId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getSpeciesName()), "species_name", queryVo.getSpeciesName());
        queryWrapper.like(null !=queryVo.getCountyId(), "county_id", queryVo.getCountyId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCountyName()), "county_name", queryVo.getCountyName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCityName()), "city_name", queryVo.getCityName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getProvinceName()), "province_name", queryVo.getProvinceName());
        List<SpeciesCountyInfo> data =this.sortInfoService.list(queryWrapper);
        return new DataGridView(page.getTotal(),data);
    }
}
