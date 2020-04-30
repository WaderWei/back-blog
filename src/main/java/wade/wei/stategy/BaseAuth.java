package wade.wei.stategy;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * 权限判断
 */
public interface BaseAuth {

    /**
     * 判断是否允许登录
     * @param request
     * @return
     */
    Boolean isAllow(HttpServletRequest request);
}
