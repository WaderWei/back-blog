package wade.wei.commresult;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Administrator
 * 统一的分页返回结果
 */
@Getter
@NoArgsConstructor
public class PageResultBean<T> extends ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总页数
     */
    private Long pageSize;

    /**
     * 总记录数
     */
    private long totalRecord;
    /**
     * 当前页码
     */
    private long pageNo;

    public PageResultBean(IPage<T> pageInfo) {
        super.setData((T) pageInfo.getRecords());
        this.setpageSize(pageInfo.getSize())
                .setTotalRecord(pageInfo.getTotal())
                .setPageNo(pageInfo.getCurrent());
    }

    public PageResultBean setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        return this;
    }

    public PageResultBean setpageSize(long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageResultBean setPageNo(long pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    @Override
    public PageResultBean setMsg(String msg) {
        super.setMsg(msg);
        return this;
    }

    @Override
    public PageResultBean setCode(Integer code) {
        super.setCode(code);
        return this;
    }

    @Override
    public PageResultBean setData(T data) {
        super.setData(data);
        return this;
    }
}
