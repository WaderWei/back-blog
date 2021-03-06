package wade.wei.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Administrator
 * 公共的返回code
 */

@Getter
@AllArgsConstructor
public enum CommonReturnEnum {
    SUCCESS(200, "OK"),
    UNKNOWN_FAIL(-1, "未知异常"),
    NEED_LOGIN(30001, "尚未登录"),
    ILLEGAL_TOKEN(50001, "非法的token"),
    ILLEGAL_URL(50003, "无效路径"),
    EXPIRED_TOKEN(50011, "Token过期了"),
    NO_PERMISSION(60001, "无权限"),
    PARAM_ERROR(70001, "参数错误"),
    EXIST_USER(80001, "此邮箱已被注册"),
    EXIST_CODE(80005,"验证码已发送，稍后重试"),
    EMAIL_OR_PWD_ERROR(80002, "邮箱或者密码错误"),
    CODE_ERROR(90001,"验证码错误");

    private Integer code;
    private String msg;
}
