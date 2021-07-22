package top.waxijiang.rush.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.PermissionDao;
import top.waxijiang.rush.entity.Permission;
import top.waxijiang.rush.service.PermissionService;

import java.util.List;

/**
 * @author waxijiang
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> findPermissionsByRoleId(Integer id) {
        return permissionDao.findPermsByRoleId(id);
    }
}
