package com.plantsys.service;

import com.plantsys.entity.Statistics;

import java.util.List;

public interface StatisticsService {

    List<Statistics> selectByDay(int indicatorId, int plantId);

    List<Statistics> selectByMonth(int indicatorId, int plantId);

    List<Statistics> selectByYear(int indicatorId, int plantId);

}
