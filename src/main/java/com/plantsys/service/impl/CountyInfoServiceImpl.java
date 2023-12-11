package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.CountyInfo;
import com.plantsys.service.CountyInfoService;
import com.plantsys.mapper.CountyInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 18035
* @description 针对表【county_info】的数据库操作Service实现
* @createDate 2023-12-11 19:31:38
*/
@Service
public class CountyInfoServiceImpl extends ServiceImpl<CountyInfoMapper, CountyInfo>
    implements CountyInfoService{

}




