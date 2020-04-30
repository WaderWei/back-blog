package wade.wei.stategy;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * 所有的都通过
 */
@Component("pub")
public class PubAuth implements BaseAuth {
    @Override
    public Boolean isAllow(HttpServletRequest request) {
        System.out.println("pub");
        return true;
    }
}
