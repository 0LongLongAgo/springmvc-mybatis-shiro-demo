package com.lq.serviceimpl;

import com.lq.dao.UserDao;
import com.lq.model.User;
import com.lq.service.UserService;
import com.lq.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by zn on 2016/4/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHelper passwordHelper;
    @Override
    public long createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        long user1 = userDao.createUser(user);
        return user1;
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId,roleIds);
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        userDao.uncorrelationRoles(userId,roleIds);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }

//    public int addUser(User user) {
//
//        return userDao.addUser(user);
//    }
//
//    public User findUserById(Integer id) {
//        return userDao.findUserById(id);
//    }
//
//    public User findByUsername(String name) {
//        return userDao.findByUsername(name);
//    }
//
//    @Override
//    public List<User> selectAll() {
//        return userDao.selectAll();
//    }
}
//@Service
//public class UserSeviceImpl implements UserService {
//    @Resource
//    private UserDao userDao;
//
//    @Override
//    public List<User> selectAll() {
//        return userDao.selectAll();
//    }
//}


