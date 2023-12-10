package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Treatment;
import com.plantsys.service.TreatmentService;
import com.plantsys.mapper.TreatmentMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【treatment】的数据库操作Service实现
* @createDate 2023-12-10 01:26:57
*/
@Service
public class TreatmentServiceImpl extends ServiceImpl<TreatmentMapper, Treatment>
    implements TreatmentService{

}




