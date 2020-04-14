package wade.wei.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import wade.wei.entity.User;
import wade.wei.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * 自定义shiro数据源
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private  UserService userService;

    /**
     * 进行权限校验的时候调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("doGetAuthorizationInfo");
        // 1. 获得当前用户
        User user = (User) principals.getPrimaryPrincipal();

        // 2. 封装角色集合
        Set<String> role = user.getRoles().stream().map(u -> u.getRoleName()).collect(Collectors.toSet());

        // 3. 封装权限认证
        return new SimpleAuthorizationInfo(role);
    }

    /**
     * 用户登录的时候会调用
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        System.out.println("进入doGetAuthenticationInfo");
        // 1. 从token中获取用户信息
        String phoneNum = token.getPrincipal().toString();
        // 2. 根据手机号获取用户信息
        User user = userService.getUserByPhone(phoneNum);
        if (user != null) {
            // 1. redis做缓存管理，SimpleAuthenticationInfo必须用主体user，而不能用username
            // 2. user实体类必须实现序列化
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getUserPassword(), this.getClass().getName());
        }
        return simpleAuthenticationInfo;
    }
}
