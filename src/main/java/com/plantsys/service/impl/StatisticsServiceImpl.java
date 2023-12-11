package com.plantsys.service.impl;

import com.plantsys.entity.Statistics;
import com.plantsys.mapper.StatisticsMapper;
import com.plantsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsMapper mapper;

    @Override
    public List<Statistics> selectByDay(int indicatorId, int plantId) {
        return mapper.selectByDay(indicatorId,plantId);
    }

    @Override
    public List<Statistics> selectByMonth(int indicatorId, int plantId) {
        return mapper.selectByMonth(indicatorId,plantId);
    }

    @Override
    public List<Statistics> selectByYear(int indicatorId, int plantId) {
        return mapper.selectByYear(indicatorId,plantId);
    }
}
