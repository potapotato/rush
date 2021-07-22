package top.waxijiang.rush.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.waxijiang.rush.entity.Permission;

import java.util.List;

/**
 * @author waxijiang
 */
@Repository
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

    /**
     * 根据角色id查询权限集合
     *
     * @param id 角色ｉｄ
     * @return 权限List
     */
    List<Permission> findPermsByRoleId(Integer id);
}