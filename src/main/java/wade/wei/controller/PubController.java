package wade.wei.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wade.wei.commresult.ResultBean;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.exception.BusinessException;
import wade.wei.vo.UserVO;

import java.io.Serializable;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("pub")
public class PubController {
    /**
     * 未登录调用
     */
    @GetMapping("needLogin")
    public ResultBean<String> needLogin() {
        //throw new BusinessException(CommonReturnEnum.NEED_LOGIN);
        return new ResultBean<>(CommonReturnEnum.NEED_LOGIN);
    }

    /**
     * 没有权限调用
     */
    @GetMapping("notPermit")
    public void notPermit() {
        throw new BusinessException(CommonReturnEnum.NO_PERMISSION);
    }

    @PostMapping("login")
    public ResultBean<Serializable> login(@Validated UserVO userVO) {
        /*Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getPhone(), userVO.getPassword());
        subject.login(token);*/
        return new ResultBean<>("123213123123");
    }
}
