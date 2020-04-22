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
    EXPIRED_TOKEN(50011, "Token过期了"),
    NO_PERMISSION(60001, "无权限"),
    PARAM_ERROR(70001, "参数错误"),
    EXIST_USER(80001, "此邮箱已被注册");

    private Integer code;
    private String msg;
}
