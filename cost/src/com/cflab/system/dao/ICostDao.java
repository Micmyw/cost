package com.cflab.system.dao;

import com.cflab.domain.Cost;

import java.util.List;

public interface ICostDao {
    int addCost(Cost cost);
    int deleteCost(Cost cost);
    int updateCost(Cost cost);
    List<Cost> queryCost(Cost cost);
}
