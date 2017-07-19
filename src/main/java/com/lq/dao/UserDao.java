package com.lq.dao;


import com.lq.model.User;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

//    List<User> selectAll();
//
//    /**
//     * 添加新用户
//     * @param username
//     * @return
//     */
//    //使用UserMapper.xml文件配置sql语句
//    @Select("select * from user where username = #{username}")
//    public User findByUsername(String username);
//
//    @Insert("insert into user(username, password, nickname) values(#{username}, #{password}, #{nickname})")
//    public int addUser(User user);
//
//    @Select("select r.name from user u,role r,user_role ur where u.id = ur.userid and r.id = ur.roleid and u.username = #{username}")
//    public Set<String> findUserRoles(String username);
//
//    @Select("select p.url from user u, role r, permission p, user_role ur, role_permission rp where u.username= #{username} and u.id=ur.userid and r.id=ur.roleid and r.id=rp.roleid and p.id=rp.permissionid")
//    public Set<String> findUserPermissions(String username);
//
//    @Select("select * from user where nickname = #{nickname}")
//    public User findByNickname(String nickname);
//
//    @Select("select * from user where id = #{id}")
//    public User findUserById(Integer id);



    @Insert("insert into sys_users(username, password, salt, locked) values(#{username},#{password},#{salt},#{locked})")
    public long createUser(User user);

    @Update("update sys_users set username=#{id}, password=#{id}, salt=#{id}, locked=#{id} where id=#{id}")
    public void updateUser(User user);

    @Delete("delete from sys_users where id=#{userId}")
    public void deleteUser(Long userId);

    @Insert("insert into sys_users_roles(user_id, role_id) values(#{userId},#{roleIds})")
    public void correlationRoles(Long userId, Long... roleIds);

    @Delete("delete from sys_users_roles where user_id=#{userId} and role_id=#{roleIds}")
    public void uncorrelationRoles(Long userId, Long... roleIds);

    @Select("select id, username, password, salt, locked from sys_users where id=#{userId}")
    User findOne(Long userId);

    @Select("select id, username, password, salt, locked from sys_users where username=#{username}")
    User findByUsername(String username);

    @Select( "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=#{username} and u.id=ur.user_id and r.id=ur.role_id")
    Set<String> findRoles(String username);

    @Select("select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=#{username} and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id")
    Set<String> findPermissions(String username);
}
