package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Plant;
import com.plantsys.service.PlantService;
import com.plantsys.mapper.PlantMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【plant】的数据库操作Service实现
* @createDate 2023-12-09 11:42:56
*/
@Service
public class PlantServiceImpl extends ServiceImpl<PlantMapper, Plant>
    implements PlantService{

}




