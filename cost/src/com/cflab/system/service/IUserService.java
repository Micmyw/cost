package com.cflab.system.service;

import com.cflab.domain.Menu;
import com.cflab.domain.User;

import java.util.List;

public interface IUserService {
    /**
     *    添加用户的方法
     * @param user
     * @return
     */
     boolean addUser(User user);

    /**
     * 检查用户是否存在
     * @param userAccount
     * @return
     */
     boolean checkAccount(String userAccount);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User doLogin(User user);

    /**
     * 根据用户角色ID，查询该角色所有的菜单权限
     * @param roleId
     * @return
     */
    List<Menu> queryUserMenus(int roleId);

    List<User> queryUser (User user);

    boolean updateUser (User user);

    boolean deleteUser (User user);
}
