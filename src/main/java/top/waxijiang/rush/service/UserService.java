package top.waxijiang.rush.service;

import top.waxijiang.rush.entity.Role;
import top.waxijiang.rush.entity.User;

import java.util.List;

/**
 * @author waxijiang
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return 成功与否
     */
    boolean register(User user);

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户对象
     */
    User findUserByUsername(String username);
}
