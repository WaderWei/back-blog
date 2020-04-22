package wade.wei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wade.wei.commresult.ResultBean;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.service.PubServer;
import wade.wei.service.UserService;
import wade.wei.utils.NumberUtils;
import wade.wei.vo.UserVO;
import wade.wei.vo.group.RegisterGroup;

import javax.validation.Valid;
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

    @Autowired
    private PubServer pubServer;

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

    @PostMapping("code")
    public ResultBean<Boolean> getCode(@Validated UserVO userVO) {
        boolean b = this.userService.existEmail(userVO.getEmail());
        // false 已存在
        if (!b) {
            return new ResultBean<>(CommonReturnEnum.EXIST_USER);
        }
        this.pubServer.sentCode(userVO.getEmail());
        return new ResultBean<>(true);
    }

    @PostMapping("register")
    public ResultBean<Boolean> register(@Validated(value = {Default.class, RegisterGroup.class}) UserVO userVO) {
        return null;
    }

}
