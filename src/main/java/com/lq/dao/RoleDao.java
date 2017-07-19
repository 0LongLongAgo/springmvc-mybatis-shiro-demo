package com.lq.dao;


import com.lq.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public interface RoleDao {

    @Insert("insert into sys_roles(role, description, available) values(#{role},#{description},#{available})")
    public Role createRole(Role role);

    @Delete("delete from sys_roles where id=#{id}")
    public void deleteRole(Long id);

    @Insert("insert into sys_roles_permissions(role_id, permission_id) values(#{roleId},#{permissionIds})")
    public void correlationPermissions(Long roleId, Long... permissionIds);

    @Delete("delete from sys_roles_permissions where role_id=#{roleId} and permission_id=#{permissionIds}")
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
