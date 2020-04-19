package wade.wei.exception;

import wade.wei.commresult.ResultBean;
import wade.wei.enums.CommonReturnEnum;

/**
 * @author Administrator
 * 业务异常类
 */
public class BusinessException extends RuntimeException {
    private CommonReturnEnum commonReturnEnum;

    public BusinessException(CommonReturnEnum commonReturnEnum) {
        this.commonReturnEnum = commonReturnEnum;
    }

    public CommonReturnEnum getCommonReturnEnum() {
        return commonReturnEnum;
    }

    public void setCommonReturnEnum(CommonReturnEnum commonReturnEnum) {
        this.commonReturnEnum = commonReturnEnum;
    }

    /**
     * 全局捕获异常直接调用此方法即可
     * @return
     */
    public ResultBean getResultBean() {
        return new ResultBean(this);
    }

}
