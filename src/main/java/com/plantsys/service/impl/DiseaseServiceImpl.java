package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Disease;
import com.plantsys.service.DiseaseService;
import com.plantsys.mapper.DiseaseMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【disease】的数据库操作Service实现
* @createDate 2023-12-10 00:18:34
*/
@Service
public class DiseaseServiceImpl extends ServiceImpl<DiseaseMapper, Disease>
    implements DiseaseService{

}




