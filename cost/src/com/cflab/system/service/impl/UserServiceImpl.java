package com.cflab.system.service.impl;

import com.cflab.domain.Menu;
import com.cflab.domain.User;
import com.cflab.system.dao.IUserDao;
import com.cflab.system.dao.Impl.UserDaoImpl;
import com.cflab.system.service.IUserService;

import java.util.List;

/**
 * 用户业务逻辑层
 * 逻辑层调用DAO层
 */
public class UserServiceImpl implements IUserService {
//    调用Dao层数据
    IUserDao userDao = new UserDaoImpl();

    /**
     * 重写添加方法
     * @param user
     * @return 返回添加的状态
     */
    @Override
    public boolean addUser(User user) {
        int row = userDao.addUser(user);
        if (row>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAccount(String userAccount) {
        int count= userDao.checkAccount(userAccount);
        if (count>0) {
            return true;
        }
        return false;
    }

    @Override
    public User doLogin(User user) {
        return userDao.doLogin(user);
    }

    @Override
    public List<Menu> queryUserMenus(int roleId) {
        return userDao.queryUserMenus(roleId);
    }

    @Override
    public List<User> queryUser(User user) {
        return userDao.queryUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        int rows = userDao.updateUser(user);
//        System.out.println(rows);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        int rows = userDao.deleteUser(user);
        if (rows>0) {
            return true;
        }
        return false;
    }
}
