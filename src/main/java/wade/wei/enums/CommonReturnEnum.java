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
    ILLEGAL_TOKEN(50001, "非法的token"),
    EXPIRED_TOKEN(50011, "Token过期了"),
    NO_PERMISSION(60021, "无权限");

    private Integer code;
    private String msg;
}
