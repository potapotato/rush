package top.waxijiang.rush.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;
import top.waxijiang.rush.entity.User;
import top.waxijiang.rush.service.UserService;
import top.waxijiang.rush.utils.ApplicationContextUtils;

/**
 * @author waxijiang
 */
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findUserByUsername(principal);
        if (!ObjectUtils.isEmpty(user)) {
            // 这里返回user对象更方便前端使用
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        } else {
            return null;
        }
    }
}
