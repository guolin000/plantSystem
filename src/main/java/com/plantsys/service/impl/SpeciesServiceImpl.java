package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Species;
import com.plantsys.service.SpeciesService;
import com.plantsys.mapper.SpeciesMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【species】的数据库操作Service实现
* @createDate 2023-12-09 11:43:08
*/
@Service
public class SpeciesServiceImpl extends ServiceImpl<SpeciesMapper, Species>
    implements SpeciesService {

}




