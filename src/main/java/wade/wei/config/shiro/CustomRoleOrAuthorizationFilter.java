package wade.wei.config.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 * @author Administrator
 * 自定义角色权限认证过滤器：即一个用户有多个角色时，其中一个角色能访问即可认证通过
 */
public class CustomRoleOrAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        //1. 获取当前主体
        Subject subject = super.getSubject(request, response);

        //2. 获取当前访问路径所需要的角色集合
        String[] roleArray = (String[]) mappedValue;

        //3. 没有角色限制可以直接访问
        if (mappedValue == null || roleArray.length == 0) {
            return true;
        }
        //4. 判断roleArray中只要有一个角色存在于subject中，即返回true
        return Arrays.stream(roleArray).anyMatch(s -> subject.hasRole(s));
    }
}
