package wade.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@TableName("tb_comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("comment_content")
    private String commentContent;

    @TableField("comment_date")
    private Date commentDate;

    @TableField("user_id")
    private Long userId;

    @TableField("blog_id")
    private Long blogId;

    @TableField("parent_id")
    private Long parentId;
}
