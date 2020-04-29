package wade.wei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wade.wei.commresult.ResultBean;
import wade.wei.entity.User;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.properties.JwtProperties;
import wade.wei.service.PubServer;
import wade.wei.service.UserService;
import wade.wei.utils.CookieUtils;
import wade.wei.utils.JwtUtils;
import wade.wei.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PubServer pubServer;

    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("pub/verifyExist")
    public ResultBean<Boolean> verifyExist(String email) {
        return new ResultBean<>(this.userService.existEmail(email));
    }

    @PostMapping("pub/code")
    public ResultBean<Boolean> getCode(@Validated UserVO userVO) {
        boolean b = this.userService.existEmail(userVO.getEmail());
        // false 已存在
        if (!b) {
            return new ResultBean<>(CommonReturnEnum.EXIST_USER);
        }
        this.pubServer.sentCode(userVO.getEmail());
        return new ResultBean<>(true);
    }

    @GetMapping("authc/info")
    public ResultBean<User> getInfo(@CookieValue("BL_TOKEN") String token,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // 公钥获取用户
        User user = JwtUtils.getUser(jwtProperties.getPublicKey(), token);
        // 刷新token
        String newToken = JwtUtils.generateToken(user, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
        //将新的Token写入cookie中，并设置httpOnly
        CookieUtils.newBuilder(response)
                .httpOnly().maxAge(jwtProperties.getCookieMaxAge())
                .request(request).build(jwtProperties.getCookieName(), newToken);
        user.setUserPassword(null);
        user.setUserSalt(null);
        return new ResultBean<>(user);
    }


}
