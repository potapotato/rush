package top.waxijiang.rush.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.waxijiang.rush.entity.Permission;

import java.util.List;

/**
 * @author waxijiang
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 通过role id查询权限
     * @param id 角色ｉｄ
     * @return 权限list
     */
    List<Permission> findPermissionsByRoleId(Integer id);
}
