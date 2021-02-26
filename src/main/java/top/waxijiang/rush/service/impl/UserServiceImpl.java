package top.waxijiang.rush.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.UserDao;
import top.waxijiang.rush.entity.Role;
import top.waxijiang.rush.entity.User;
import top.waxijiang.rush.service.UserService;
import top.waxijiang.rush.utils.SaltUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author waxijiang
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean register(User user) {
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        return userDao.insert(user) > 0;
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("enabled", true);
        List<User> users = userDao.selectByCondition(map);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            user.setPassword(null);
            users.set(i, user);
        }
        return users;
    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }
}
