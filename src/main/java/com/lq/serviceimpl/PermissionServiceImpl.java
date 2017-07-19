package com.lq.serviceimpl;

import com.lq.dao.PermissionDao;
import com.lq.model.Permission;
import com.lq.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuqun on 2017/5/15.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;
    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
