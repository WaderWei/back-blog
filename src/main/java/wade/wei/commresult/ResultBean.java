package wade.wei.commresult;

import lombok.Data;
import lombok.NoArgsConstructor;
import wade.wei.enums.CommonReturnEnum;

import java.io.Serializable;

/**
 * @author Administrator
 * 统一返回结果
 */
@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回的数字
     */
    private Integer code = CommonReturnEnum.SUCCESS.getCode();

    /**
     * 返回的信息
     */
    private String msg = CommonReturnEnum.SUCCESS.getMsg();

    /**
     * 返回的数据
     */
    private T data;

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(Throwable err) {
        this.code = CommonReturnEnum.UNKNOWN_FAIL.getCode();
        this.msg = CommonReturnEnum.UNKNOWN_FAIL.getMsg();
    }

    /**
     * 设置返回数字
     *
     * @param code
     * @return
     */
    public ResultBean<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResultBean<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultBean<T> setData(T data) {
        this.data = data;
        return this;
    }
}
