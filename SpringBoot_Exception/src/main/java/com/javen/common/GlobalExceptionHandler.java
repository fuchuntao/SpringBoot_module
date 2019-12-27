package com.javen.common;

import com.javen.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 缺少请求参数异常
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(
            MissingServletRequestParameterException exception) {
        logger.error("缺少请求参数，{}", exception.getMessage());
        return new JsonResult("400", "缺少必要的请求参数");
    }

    /**
     * 空指针异常
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleTypeMismatchException(NullPointerException exception) {
        logger.error("空指针异常，{}", exception.getMessage());
        return new JsonResult("500", "空指针异常了");
    }

    /**
     * 拦截业务异常，返回业务异常信息
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(CustomErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(CustomErrorException exception) {
        int code = exception.getCode();
        String message = exception.getMessage();
        return new JsonResult(code, message);
    }

    @ExceptionHandler(ViewErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView defaultErrorHandler(ViewErrorException exception) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("code", exception.getCode());
        mav.addObject("msg", exception.getMessage());
        return mav;
    }
}