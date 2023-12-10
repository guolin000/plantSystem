package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.City;
import com.plantsys.service.CityService;
import com.plantsys.mapper.CityMapper;
import org.springframework.stereotype.Service;

/**
* @author 18035
* @description 针对表【city】的数据库操作Service实现
* @createDate 2023-12-10 12:53:21
*/
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
    implements CityService{

}




