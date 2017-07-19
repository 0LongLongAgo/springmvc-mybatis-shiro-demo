package com.lq.service;

import com.lq.model.Permission;

/**
 * Created by liuqun on 2017/5/15.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
