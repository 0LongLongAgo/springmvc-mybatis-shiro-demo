package com.lq.service;

import com.lq.model.Role;

/**
 * Created by liuqun on 2017/5/15.
 */
public interface RoleService {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);
    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
