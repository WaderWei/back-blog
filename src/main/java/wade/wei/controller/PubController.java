package wade.wei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wade.wei.commresult.ResultBean;
import wade.wei.service.UserService;
import wade.wei.utils.NumberUtils;
import wade.wei.vo.UserVO;
import wade.wei.vo.group.RegisterGroup;

import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("pub")
public class PubController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResultBean<Serializable> login(@Validated UserVO userVO) {
        /*Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getPhone(), userVO.getPassword());
        subject.login(token);*/
        return new ResultBean<>("123213123123");
    }

    @GetMapping("verifyExist")
    public ResultBean<Boolean> verifyExist(String email) {
        return new ResultBean<>(this.userService.existEmail(email));
    }

    @GetMapping("code")
    public ResultBean<Boolean> getCode(@Validated UserVO userVO) {
        String code = NumberUtils.generateCode(5);
        // 1. 存储到redis中
        // 2. 发送到rabbitmq中
        // 3. 返回用户
        return new ResultBean<>(true);
    }

    @PostMapping("register")
    public ResultBean<Boolean> register(@Validated(value = {Default.class, RegisterGroup.class}) UserVO userVO) {
        return null;
    }

}
