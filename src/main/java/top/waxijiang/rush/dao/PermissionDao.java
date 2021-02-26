package top.waxijiang.rush.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.waxijiang.rush.entity.Permission;

import java.util.List;

@Repository
@Mapper
public interface PermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

        /**
     * 根据角色id查询权限集合
     * @param id 角色ｉｄ
     * @return 权限List
     */
    List<Permission> findPermsByRoleId(Integer id);
}