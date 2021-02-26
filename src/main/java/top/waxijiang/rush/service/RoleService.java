package top.waxijiang.rush.service;

import top.waxijiang.rush.entity.Role;

import java.util.List;

/**
 * @author waxijiang
 */
public interface RoleService {
    /**
     * 通过用户名查找角色
     *
     * @param username 　用户名
     * @return 角色List
     */
    List<Role> findRolesByUsername(String username);
}
