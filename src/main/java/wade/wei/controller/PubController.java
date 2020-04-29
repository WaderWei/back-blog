package wade.wei.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wade.wei.commresult.ResultBean;
import wade.wei.entity.User;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.exception.BusinessException;
import wade.wei.properties.JwtProperties;
import wade.wei.service.PubServer;
import wade.wei.service.UserService;
import wade.wei.utils.CookieUtils;
import wade.wei.utils.NumberUtils;
import wade.wei.vo.UserVO;
import wade.wei.vo.group.RegisterGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("login")
    public ResultBean<Boolean> login(@Validated UserVO userVO,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        User user = new User();
        user.setUserEmail(userVO.getEmail());
        user.setUserPassword(userVO.getPassword());
        String token = pubServer.generateToken(userService.getUser(user));
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(CommonReturnEnum.EMAIL_OR_PWD_ERROR);
        }
        //将Token写入cookie中
        CookieUtils.newBuilder(response).httpOnly().maxAge(jwtProperties.getCookieMaxAge())
                .request(request).build(jwtProperties.getCookieName(), token);
        return new ResultBean<>(true);
    }

    @PostMapping("register")
    public ResultBean<Boolean> register(@Validated(value = {Default.class, RegisterGroup.class}) UserVO userVO) {
        User user = new User();
        user.setUserEmail(userVO.getEmail());
        user.setUserPassword(userVO.getPassword());
        this.userService.saveUser(user, userVO.getVerificationCode());
        return new ResultBean<>(true);
    }

}
