package wade.wei.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import wade.wei.utils.JsonMapperUtil;
import wade.wei.utils.ThreadLocalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 * 打印每次请求花费的事件
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("--------------------start-----------------");
        String url = request.getRequestURL().toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("request start. url: {} , parameter: {}", url, JsonMapperUtil.obj2String(parameterMap));
        ThreadLocalUtil.add(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String url = request.getRequestURL().toString();
        log.info("request end. url: {} , cost: {}", url, System.currentTimeMillis() - ThreadLocalUtil.getTime());
        ThreadLocalUtil.remove();
        log.info("--------------------end-----------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
