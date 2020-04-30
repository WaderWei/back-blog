package wade.wei.stategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 */
@Component
public class AuthFactory {

    @Autowired
    private Map<String, BaseAuth> map = new ConcurrentHashMap<>();

    public Boolean getAuthResultFactory(HttpServletRequest request) {
        String uri = null;
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        for (String s : split) {
            if (StringUtils.equals(s, "pub")) {
                uri = "pub";
                break;
            }
            if (StringUtils.equals(s, "authc")) {
                uri = "login";
                break;
            }
            if (StringUtils.equals(s, "VIP")) {
                uri = "vip";
                break;
            }
        }
        if (uri == null) {
            throw new BusinessException(CommonReturnEnum.ILLEGAL_URL);
        }
        return map.get(uri).isAllow(request);
    }
}
