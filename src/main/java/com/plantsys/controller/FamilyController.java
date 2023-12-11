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
@RequestMapping("sort")
public class FamilyController {
    @Autowired
    FamilyService familyService;

    @Autowired
    GenusService genusService;

    @Autowired
    SpeciesService speciesService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    CityService cityService;

    @Autowired
    CountyService countyService;

    // 添加科
    @RequestMapping("addFamily")
    public ResultObj addFamily(Family family) {
        QueryWrapper<Family> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("family_id",family.getFamilyId());
        int count=this.familyService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"科已存在");
        }
        try {
            familyService.save(family);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    // 更新科
    @RequestMapping("updateFamily")
    public ResultObj updateFamily(Family family) {
        try {
            familyService.updateById(family);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }
    @RequestMapping("familyList")
    public DataGridView familyList(QueryVo queryVo) {
        System.out.println("awfdawdaw");
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getFamilyId(), "family_id", queryVo.getFamilyId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getFamilyName()), "family_name", queryVo.getFamilyName());
        List<Family> data =this.familyService.list(queryWrapper);
        return new DataGridView(page.getTotal(),data);
    }
    // 删除科
    @RequestMapping("deleteFamily")
    public ResultObj deleteFamily(Integer familyId) {
        try {
            // 查询该科下的所有属
            QueryWrapper<Genus> genusWrapper = new QueryWrapper<>();
            genusWrapper.eq("family_id", familyId);
            List<Genus> genera = genusService.list(genusWrapper);

            // 遍历删除属及其下属种
            for (Genus genus : genera) {
                // 查询该属下的所有种
                QueryWrapper<Species> speciesWrapper = new QueryWrapper<>();
                speciesWrapper.eq("genus_id", genus.getGenusId());
                List<Species> speciesList = speciesService.list(speciesWrapper);

                // 遍历删除种
                for (Species species : speciesList) {
                    speciesService.removeById(species.getSpeciesId());
                }

                // 删除属
                genusService.removeById(genus.getGenusId());
            }
            //获得所在属的id
            //genusList(familyId)
            familyService.removeById(familyId);
            // 你可能需要处理级联删除或其他相关操作
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
