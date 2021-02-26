package top.waxijiang.rush.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.UserDao;
import top.waxijiang.rush.entity.Role;
import top.waxijiang.rush.entity.User;
import top.waxijiang.rush.service.UserService;
import top.waxijiang.rush.utils.SaltUtils;

import java.util.List;

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
}
