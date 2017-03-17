package com.springboot.ch3.exception;

import com.springboot.ch3.entity.ErrorInfo;
import com.springboot.ch3.entity.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhouhy
 *
 * 全局异常处理类
 * @create 2017 -03-17 下午12:32
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW="error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e){
        ModelAndView mav=new ModelAndView();
        mav.addObject("exception",e);
        mav.addObject("url",request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request,Exception e){
        ErrorInfo<String> r=new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setUrl(request.getRequestURL().toString());
        r.setData("Some data");
        return r;
    }

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public ErrorInfo<User> jsonuserErrorHandler(HttpServletRequest request, Exception e){
        ErrorInfo<User> r=new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setUrl(request.getRequestURL().toString());
        User user=new User();
        user.setId(2L);
        user.setName("tom");
        user.setAge(23);
        r.setData(user);
        return r;
    }
}
