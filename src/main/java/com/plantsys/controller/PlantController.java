package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.FamilyPlantCountVo;
import com.plantsys.Vo.PlantInfoVo;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.*;
import com.plantsys.service.*;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("plant")
public class PlantController {


    @Autowired
    PlantService plantService;

    @Autowired
    GenusService genusService;
    @Autowired
    FamilyService familyService;
    @Autowired
    DiseaseService DiseaseService;
    @Autowired
    PictureService pictureService;
    @Autowired
    MaintenanceTaskService maintenanceTaskService;
    @Autowired
    MonitorValueService monitorValueService;
    @Autowired
    PlantInfoService plantInfoService;

    @Autowired
    FamilyPlantCountService familyPlantCountService;

    //添加植物
    @RequestMapping("addPlant")
    public ResultObj addPlant(Plant plant){
        QueryWrapper<Plant> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("plant_id",plant.getPlantId());
        int count=this.plantService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"植物已存在");
        }
        try{
            this.plantService.save(plant);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }



    //修改植物
    @RequestMapping("updatePlant")
    public ResultObj update(Plant plant){
        try{
            this.plantService.updateById(plant);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }
    //删除植物
    @RequestMapping("deletePlant")
    public ResultObj deletePlant(Integer plantId){

        try{
            System.out.println(plantId);
            Plant plant=this.plantService.getById(plantId);
            this.pictureService.deleteByPlantId(plantId);
            this.maintenanceTaskService.deleteByPlantId(plantId);
            this.monitorValueService.deleteByPlantId(plantId);

            this.plantService.removeById(plantId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    //查询植物
    @RequestMapping("plantList")
    public DataGridView plantList(PlantInfoVo plantInfoVo){
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");

        Page<Object> page = PageHelper.startPage(plantInfoVo.getPage(), plantInfoVo.getLimit());
        QueryWrapper<PlantInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != plantInfoVo.getPlantId(), "plant_id", plantInfoVo.getPlantId());
        queryWrapper.like(StrUtil.isNotBlank(plantInfoVo.getPlantName()), "plant_name", plantInfoVo.getPlantName());
        queryWrapper.like(StrUtil.isNotBlank(plantInfoVo.getFeature()), "feature", plantInfoVo.getFeature());
        queryWrapper.like(StrUtil.isNotBlank(plantInfoVo.getValue()), "value", plantInfoVo.getValue());
        queryWrapper.like(StrUtil.isNotBlank(plantInfoVo.getPoint()), "point", plantInfoVo.getPoint());
        queryWrapper.like(StrUtil.isNotBlank(plantInfoVo.getAlias()), "alias", plantInfoVo.getAlias());
        List<PlantInfo> data =this.plantInfoService.list(queryWrapper);
        return new DataGridView(page.getTotal(),data);

    }

    @RequestMapping("showPictures")
    public List<Picture> viewPictures(@RequestParam("plantId") int plantId, Model model){
        List<Picture> pictures = this.pictureService.selectAllByPlantId(plantId);
        model.addAttribute("pictures", pictures);
        return pictures;
    }
    @RequestMapping("addPlantPicture")
    public ResultObj addPlantPicture(Picture picture){
        QueryWrapper<Picture> wrapper=new QueryWrapper<>();
        wrapper.eq("picture_id",picture.getPictureId());
        int count = this.pictureService.count(wrapper);
        System.out.println("picture.getPlantId()"+picture.getPlantId());
        if(count>0){
            return new ResultObj(-1,"图片已存在");
        }
        try{
            this.pictureService.save(picture);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("deletePicture")
    public ResultObj deletePicture(Integer pictureId){
        try{
            System.out.println("pictureId:"+pictureId);
            this.pictureService.removeById(pictureId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("familyPlantCounts")
    public DataGridView familyPlantCounts(FamilyPlantCountVo familyPlantCountVo){
            Page<Object> page = PageHelper.startPage(familyPlantCountVo.getPage(), familyPlantCountVo.getLimit());
            QueryWrapper<FamilyPlantCount> queryWrapper = new QueryWrapper<>();
            // 模糊查询或查询所有植物
            queryWrapper.like(null != familyPlantCountVo.getFamilyId(), "family_id", familyPlantCountVo.getFamilyId());
            queryWrapper.like(null != familyPlantCountVo.getPlantNum(), "plant_num", familyPlantCountVo.getPlantNum());
            queryWrapper.like(StrUtil.isNotBlank(familyPlantCountVo.getFamilyName()), "family_name", familyPlantCountVo.getFamilyName());


            List<FamilyPlantCount> data =this.familyPlantCountService.list(queryWrapper);
            return new DataGridView(page.getTotal(),data);
    }
}
