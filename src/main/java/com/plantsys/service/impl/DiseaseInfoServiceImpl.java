package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.DiseaseInfo;
import com.plantsys.service.DiseaseInfoService;
import com.plantsys.mapper.DiseaseInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【disease_info】的数据库操作Service实现
* @createDate 2023-12-10 00:28:27
*/
@Service
public class DiseaseInfoServiceImpl extends ServiceImpl<DiseaseInfoMapper, DiseaseInfo>
    implements DiseaseInfoService{

}




