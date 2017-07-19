package com.lq.service;


import com.lq.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by zn on 2016/4/11.
 */
public interface UserService {
    //    public List<User> selectAll();
    public long createUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);

    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
