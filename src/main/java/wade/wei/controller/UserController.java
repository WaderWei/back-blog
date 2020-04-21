package wade.wei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wade.wei.commresult.ResultBean;
import wade.wei.entity.User;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/authc/user")
public class UserController {
    @GetMapping("info")
    public ResultBean<User> getInfo() {
        // User user = (User) SecurityUtils.getSubject().getPrincipal();
        return null;
    }
}
