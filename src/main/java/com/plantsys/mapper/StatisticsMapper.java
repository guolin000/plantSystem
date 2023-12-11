package com.plantsys.mapper;

import com.plantsys.entity.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticsMapper {

    List<Statistics> selectByDay(@Param("indicatorId")int indicatorId, @Param("plantId") int plantId);

    List<Statistics> selectByMonth(@Param("indicatorId")int indicatorId, @Param("plantId") int plantId);

    List<Statistics> selectByYear(@Param("indicatorId")int indicatorId, @Param("plantId") int plantId);

}
