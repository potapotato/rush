package top.waxijiang.rush.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import top.waxijiang.rush.entity.Permission;
import top.waxijiang.rush.entity.Role;
import top.waxijiang.rush.entity.User;
import top.waxijiang.rush.service.PermissionService;
import top.waxijiang.rush.service.RoleService;
import top.waxijiang.rush.service.UserService;
import top.waxijiang.rush.utils.ApplicationContextUtils;

import java.util.List;

/**
 * @author waxijiang
 */
public class CustomerRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(CustomerRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        RoleService roleService = (RoleService) ApplicationContextUtils.getBean("roleService");
        PermissionService permissionService = (PermissionService) ApplicationContextUtils.getBean("permissionService");

        List<Role> roles = roleService.findRolesByUsername(primaryPrincipal.getUsername());
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getName());
            List<Permission> permissions = permissionService.findPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionStr());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findUserByUsername(principal);
        if (!ObjectUtils.isEmpty(user)) {
            // 这里返回user对象更方便前端使用
            logger.info("用户<" + user.getUsername() + ">登录成功");
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        } else {
            return null;
        }
    }
}
