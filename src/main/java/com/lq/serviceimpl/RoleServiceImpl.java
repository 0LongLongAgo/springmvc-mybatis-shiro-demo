package com.lq.serviceimpl;

import com.lq.dao.RoleDao;
import com.lq.model.Role;
import com.lq.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuqun on 2017/5/15.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {

        roleDao.correlationPermissions(roleId, permissionIds);
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }
}
