package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.DiseaseInfoVo;
import com.plantsys.Vo.TreatmentInfoVo;
import com.plantsys.entity.DiseaseInfo;
import com.plantsys.entity.Treatment;
import com.plantsys.entity.TreatmentInfo;
import com.plantsys.service.DiseaseInfoService;
import com.plantsys.service.TreatmentInfoService;
import com.plantsys.service.TreatmentService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("disease")
public class DiseaseController {
    @Autowired
    DiseaseInfoService diseaseInfoService;

    @Autowired
    TreatmentService treatmentService;
    @Autowired
    TreatmentInfoService treatmentInfoService;

    @RequestMapping("diseaseInfoList")
    public DataGridView diseaseInfoList(DiseaseInfoVo diseaseInfoVo){
        Page<Object> page = PageHelper.startPage(diseaseInfoVo.getPage(), diseaseInfoVo.getLimit());
        QueryWrapper<DiseaseInfo> queryWrapper = new QueryWrapper<>();

        // 模糊查询或查询所有图书
        queryWrapper.like(null != diseaseInfoVo.getPlantId(), "plant_id", diseaseInfoVo.getPlantId());
        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getName()), "name", diseaseInfoVo.getName());
        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getPlantName()), "plant_name", diseaseInfoVo.getPlantName());
        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getTreatMethod()), "treat_method", diseaseInfoVo.getTreatMethod());
        List<DiseaseInfo> data = this.diseaseInfoService.list(queryWrapper);

        return new DataGridView(page.getTotal(),data);
    }

    @RequestMapping("addTreatment")
    public ResultObj addTreatment(Treatment treatment){
        try{
            this.treatmentService.save(treatment);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("treatmentInfoList")
    public DataGridView treatmentInfoList(TreatmentInfoVo treatmentInfoVo){
        Page<Object> page = PageHelper.startPage(treatmentInfoVo.getPage(), treatmentInfoVo.getLimit());
        QueryWrapper<TreatmentInfo> queryWrapper = new QueryWrapper<>();

//        // 模糊查询或查询所有图书
//        queryWrapper.like(null != diseaseInfoVo.getPlantId(), "plant_id", diseaseInfoVo.getPlantId());
//        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getName()), "name", diseaseInfoVo.getName());
//        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getPlantName()), "plant_name", diseaseInfoVo.getPlantName());
//        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getTreatMethod()), "treat_method", diseaseInfoVo.getTreatMethod());
        List<TreatmentInfo> data = this.treatmentInfoService.list(queryWrapper);

        return new DataGridView(page.getTotal(),data);
    }
}
