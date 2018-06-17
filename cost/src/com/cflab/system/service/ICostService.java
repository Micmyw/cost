package com.cflab.system.service;

import com.cflab.domain.Cost;

import java.util.List;

public interface ICostService {
    boolean addCost(Cost cost);
    boolean deleteCost(Cost cost);
    boolean updateCost(Cost cost);
    List<Cost> queryCost(Cost cost);
}
