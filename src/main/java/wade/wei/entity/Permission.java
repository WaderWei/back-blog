package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@TableName("tb_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("perm_name")
    private String permName;

    @TableField("perm_url")
    private String permUrl;

    @TableField("perm_desc")
    private String permDesc;

    @TableField(exist = false)
    private List<Role> roles;

}
