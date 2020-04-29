package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@TableName("tb_user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_nickname")
    private String userNickname;

    @TableField("user_phone")
    private String userPhone;

    @TableField("user_email")
    private String userEmail;

    @TableField("user_birthday")
    private Date userBirthday;

    @TableField("user_register_time")
    private Date userRegisterTime;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_avatar")
    private String userAvatar;

    @TableField("user_status")
    private Byte userStatus;

    @TableField("user_salt")
    private String userSalt;

    @TableField(exist = false)
    private List<Role> roles;
}
