package wade.wei.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import wade.wei.vo.group.LoginGroup;
import wade.wei.vo.group.RegisterGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVO implements Serializable {

    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^(.+)@(.+)$",message = "请输入正确的邮箱格式")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Length(min = 5,max = 12,message = "密码的长度必须在5-12之间")
    private String password;

    @NotBlank(message = "验证码不能为空",groups = RegisterGroup.class)
    private String verificationCode;
}
