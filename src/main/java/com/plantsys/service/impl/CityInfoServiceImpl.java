package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.CityInfo;
import com.plantsys.service.CityInfoService;
import com.plantsys.mapper.CityInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 18035
* @description 针对表【city_info】的数据库操作Service实现
* @createDate 2023-12-11 19:16:32
*/
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService{

}




