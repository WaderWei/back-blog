package wade.wei.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import wade.wei.vo.group.LoginGroup;
import wade.wei.vo.group.RegisterGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVO {

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[0-9]{10}$",message = "请输入正确的手机号码")
    private String phone;

    @NotBlank(message = "密码不能为空")
    @Length(min = 5,max = 12,message = "密码的长度必须在5-12之间")
    private String password;

    @NotBlank(message = "验证码不能为空",groups = RegisterGroup.class)
    private String verificationCode;
}
