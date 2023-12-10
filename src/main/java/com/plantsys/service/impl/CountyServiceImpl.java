package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.County;
import com.plantsys.service.CountyService;
import com.plantsys.mapper.CountyMapper;
import org.springframework.stereotype.Service;

/**
* @author 18035
* @description 针对表【county】的数据库操作Service实现
* @createDate 2023-12-10 12:53:31
*/
@Service
public class CountyServiceImpl extends ServiceImpl<CountyMapper, County>
    implements CountyService{

}




