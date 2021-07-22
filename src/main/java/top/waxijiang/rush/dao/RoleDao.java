package top.waxijiang.rush.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.waxijiang.rush.entity.Permission;
import top.waxijiang.rush.entity.Role;

import java.util.List;

/**
 * @author waxijiang
 */
@Repository
@Mapper
public interface RoleDao extends BaseMapper<Role> {
    List<Role> selectRolesByUserName(String username);
}