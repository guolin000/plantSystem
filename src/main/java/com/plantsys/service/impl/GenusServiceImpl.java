package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Genus;
import com.plantsys.service.GenusService;
import com.plantsys.mapper.GenusMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【genus】的数据库操作Service实现
* @createDate 2023-12-09 11:43:16
*/
@Service
public class GenusServiceImpl extends ServiceImpl<GenusMapper, Genus>
    implements GenusService {

}




