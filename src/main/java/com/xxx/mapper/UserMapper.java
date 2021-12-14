package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.User;
import com.xxx.query.UserQuery;

import java.util.List;

public interface UserMapper extends BaseMapper<User,Integer> {

    /**
     * 列表展示用户信息
     * @param userQuery
     * @return
     */
    public List<User> getUserList(UserQuery userQuery);

    /**
     * 根据登录名查询用户
     * @param loginname
     * @return
     */
    public User getUserByName(String loginname);

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    public int deleteUserById(Integer[] userIds);

    User selectUserByLoginName(String loginName);
}