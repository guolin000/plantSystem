package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.SortInfo;
import com.plantsys.service.FamilyService;
import com.plantsys.service.GenusService;
import com.plantsys.service.SortInfoService;
import com.plantsys.service.SpeciesService;
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
    private SortInfoService sortInfoService;
    @PostMapping("addSortInfo")
    public ResultObj addSortInfo(SortInfo sortInfo) {
        QueryWrapper<SortInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("plant_id",sortInfo.getPlantId());
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
    public ResultObj updateSortInfo(SortInfo sortInfo) {
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
        QueryWrapper<SortInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getPlantId(), "plant_id", queryVo.getPlantId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPlantName()), "plant_name", queryVo.getPlantName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getFamilyName()), "family_name", queryVo.getFamilyName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getGenusName()), "genus_name", queryVo.getGenusName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getSpeciesName()), "species_name", queryVo.getSpeciesName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getProvinceName()), "province_name", queryVo.getProvinceName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCityName()), "city_name", queryVo.getCityName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCountyName()), "county_name", queryVo.getCountyName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getEnvironment()), "environment", queryVo.getEnvironment());
        List<SortInfo> data =this.sortInfoService.list(queryWrapper);
        return new DataGridView(page.getTotal(),data);
    }
}
