package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Role;
import com.plantsys.service.RoleService;
import com.plantsys.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-12-19 19:14:48
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




