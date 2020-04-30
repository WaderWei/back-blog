package wade.wei.stategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wade.wei.entity.User;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.exception.BusinessException;
import wade.wei.properties.JwtProperties;
import wade.wei.utils.CookieUtils;
import wade.wei.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录时校验
 *
 * @author Administrator
 */
@Component("login")
public class LoginAuth implements BaseAuth {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Boolean isAllow(HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(CommonReturnEnum.EXPIRED_TOKEN);
        }
        User user = JwtUtils.getUser(jwtProperties.getPublicKey(), token);
        if (user == null) {
            return false;
        }
        System.out.println(user);
        return true;
    }
}
