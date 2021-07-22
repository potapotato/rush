package top.waxijiang.rush.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.waxijiang.rush.entity.Role;
import top.waxijiang.rush.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author waxijiang
 */
@Repository
@Mapper
public interface UserDao extends BaseMapper<User> {
    User selectByUsername(String username);
}