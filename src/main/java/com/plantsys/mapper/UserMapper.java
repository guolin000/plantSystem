package com.plantsys.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plantsys.entity.User;

/**
 *
 */
public interface UserMapper extends BaseMapper<User> {
    int addSelective(User user);

    int delByUserId(@Param("userId") Integer userId);

    List<User> selectByLoginName(@Param("loginName") String loginName);

    List<User> selectByUserId(@Param("userId") Integer userId);

    int updateSelective(User user);
}
