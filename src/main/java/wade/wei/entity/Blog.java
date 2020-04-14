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
@TableName("tb_blog")
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type=IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("blog_title")
    private String blogTitle;

    @TableField("blog_content")
    private String blogContent;

    @TableField("blog_content_html")
    private String blogContentHtml;

    @TableField("category_id")
    private String categoryId;

    @TableField("blog_view_conunts")
    private String blogViewCounts;

    @TableField("blog_comment_counts")
    private String blogCommentCounts;

    @TableField("blog_date")
    private Date blogDate;

    @TableField("blog_delete_status")
    private Integer blogDeleteStatus;

    @TableField("blog_permission")
    private Integer blogPermission;

    @TableField("blog_release_status")
    private Integer blogReleaseStatus;

}
