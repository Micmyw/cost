package com.cflab.system.service.impl;

import com.cflab.domain.Cost;
import com.cflab.system.dao.ICostDao;
import com.cflab.system.dao.Impl.CostDaoImpl;
import com.cflab.system.service.ICostService;

import java.util.List;

public class CostServiceImpl implements ICostService {
    ICostDao costDao = new CostDaoImpl();
    @Override
    public boolean addCost(Cost cost) {
        int rows = costDao.addCost(cost);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCost(Cost cost) {
        int rows = costDao.deleteCost(cost);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCost(Cost cost) {
       int rows = costDao.updateCost(cost);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Cost> queryCost(Cost cost) {
        return costDao.queryCost(cost);
    }
}
