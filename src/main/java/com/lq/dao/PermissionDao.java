package com.lq.dao;


import com.lq.model.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public interface PermissionDao {

    @Insert("insert into sys_permissions(permission, description, available) values(#{permission},#{description},#{available})")
    public Permission createPermission(Permission permission);
    @Delete("delete from sys_roles_permissions where permission_id=#{permissionId}")
    public void deletePermission(Long permissionId);

}
