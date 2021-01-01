package top.waxijiang.rush.configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.waxijiang.rush.realm.CustomerRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author waxijiang
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/index");
        // 配置不被拦截页面和拦截界面
        Map<String, String> map = new LinkedHashMap<>();
        // 注意顺序, /**必须在最后
        map.put("/css/**", "anon");
        map.put("/icon/**", "anon");
        map.put("/js/**", "anon");
        map.put("/images/**", "anon");
        map.put("/img/**", "anon");

        map.put("/logout", "logout");
        map.put("/login", "anon");
        map.put("/register", "anon");
        map.put("/login.html", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("customerRealm") Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean("customerRealm")
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        //设置hashed凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置md5加密
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
