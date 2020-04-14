package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 */

@Data
@Accessors(chain = true)
@TableName("tb_label")
public class Label implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("label_name")
    private String labelName;

    @TableField("label_desc")
    private String labelDesc;

    @TableField("label_user_count")
    private Integer labelUserCount;
}
