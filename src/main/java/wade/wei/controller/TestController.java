package wade.wei.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wade.wei.commresult.ResultBean;
import wade.wei.entity.User;
import wade.wei.mapper.UserMapper;
import wade.wei.service.UserService;
import wade.wei.vo.UserVO;
import wade.wei.vo.group.RegisterGroup;

import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    /**
     * @Validated什么都不写则只会验证default下的默认分组
     * @param userVO
     * @return
     */
    @GetMapping("pub/hello")
    public ResultBean<User> hello1(@Validated UserVO userVO) {
        User user = userService.getUserByPhone(userVO.getPhone());
        // Set<String> roles = user.getRoles().stream().map(u -> u.getRoleName()).collect(Collectors.toSet());
        return new ResultBean<>(user);
    }

    /**
     * @Validated 写多少会验证多少
     * @param userVO
     * @return
     */
    @GetMapping("vip/hello2")
    public ResultBean<List<User>> hello2(@Validated({Default.class,RegisterGroup.class}) UserVO userVO) {
        System.out.println(userVO);
        return new ResultBean<>(userService.selectList());
    }

    @GetMapping("pub/needLogin")
    public ResultBean<String> needLogin(){
        return new ResultBean<>("登录页面");
    }

    @GetMapping("pub/login")
    public ResultBean<Serializable> login(@Validated UserVO userVO){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userVO.getPhone(), userVO.getPassword());
        subject.login(usernamePasswordToken);
        return new ResultBean<>(subject.getSession().getId());
    }

}
