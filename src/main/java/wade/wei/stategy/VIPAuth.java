package wade.wei.stategy;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Administrator
 */
@Component("vip")
public class VIPAuth implements BaseAuth {
    @Override
    public Boolean isAllow(HttpServletRequest request) {
        System.out.println("vip");
        return true;
    }
}
