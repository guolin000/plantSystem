package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.GenusInfo;
import com.plantsys.service.GenusInfoService;
import com.plantsys.mapper.GenusInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 18035
* @description 针对表【genus_info】的数据库操作Service实现
* @createDate 2023-12-11 13:53:06
*/
@Service
public class GenusInfoServiceImpl extends ServiceImpl<GenusInfoMapper, GenusInfo>
    implements GenusInfoService{

}




