package wade.wei.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wade.wei.commresult.ResultBean;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.exception.BusinessException;
import wade.wei.utils.JsonMapperUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final String ERROR_PRE = "GlobalExceptionHandler: ";
    private final String REQUEST_INFO = "请求相关信息：";

    @ExceptionHandler(BindException.class)
    public ResultBean parameterExceptionHandler(HttpServletRequest request, BindException e) {
        log(request,e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return new ResultBean().setCode(CommonReturnEnum.PARAM_ERROR.getCode())
                        .setMsg(CommonReturnEnum.PARAM_ERROR.getMsg() + ":" + fieldError);
            }
        }
        return new ResultBean(CommonReturnEnum.PARAM_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResultBean businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        log(request,e);
        System.out.println(e.getResultBean());
        return e.getResultBean();
    }

    @ExceptionHandler(Exception.class)
    public ResultBean exceptionHandler(HttpServletRequest request, Exception e) {
        log(request,e);
        return new ResultBean(CommonReturnEnum.UNKNOWN_FAIL);
    }

    private void log(HttpServletRequest request,Exception e){
        log.error(ERROR_PRE + e + ";" +
                REQUEST_INFO + request.getRequestURL() + "--" + JsonMapperUtil.obj2String(request.getParameterMap()));
    }
}
