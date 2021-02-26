package top.waxijiang.rush.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.RoleDao;
import top.waxijiang.rush.entity.Role;
import top.waxijiang.rush.service.RoleService;

import java.util.List;

/**
 * @author waxijiang
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findRolesByUsername(String username) {
        return roleDao.selectRolesByUserName(username);
    }
}
