package com.cflab.system.dao;

import com.cflab.domain.Menu;
import com.cflab.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 添加用户，返回受影响的行数
     * @param user 用户实体
     * @return 受影响的行数
     */
    int addUser(User user);

    /**
     * 根据用户账号判断是否存在
     * 通过查询数据库记录条数来判断是否已经存在
     * @param userAccount 用户账号
     * @return
     */
    int checkAccount(String userAccount);

    /**
     * 用户登录，根据用户密码来查询用户信息
     * @param user 用户实体
     * @return
     */
    User doLogin(User user);

    /**
     * 根据角色ID，返回角色菜单权限
     * @param roleId
     * @return
     */
    List<Menu> queryUserMenus (int roleId);

    List<User> queryUser (User user);

    int updateUser (User user);

    int deleteUser (User user);
}
