package com.fishingtime.framework.common.web;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fishingtime.framework.common.web.exception.AuthenticationException;
import com.fishingtime.framework.common.web.exception.BusiException;
import com.fishingtime.framework.common.web.response.ResultStatus;
import com.fishingtime.framework.common.web.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.util.List;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-08 16:57
 **/
@org.springframework.web.bind.annotation.RestControllerAdvice
@Slf4j
@ConditionalOnClass(javax.servlet.Servlet.class)
public class ControllerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {

    }

    /**
     * 其他未捕获的异常处理
     * @Description:
     * @param ex
     * @return
     * @author
     * @date 2019/4/10 08:56
     * @throws
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public org.springframework.http.ResponseEntity errorHandler(Exception ex)throws Exception {
        //不处理ServletException异常，在BasicErrorController中处理
        if(ex instanceof ServletException){
            throw ex;
        }
        log.error("",ex);
        return Response.fail(ResultStatus.SYSTEM_ERROR);
    }

    /**
     * errorHandler 前台传参json转换错误
     * @Description:  
     * @param ex
     * @return com.tydic.dev1.common.base.api.Response
     * @author
     * @date 2019/4/10 08:55
     * @throws
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public org.springframework.http.ResponseEntity errorHandler(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        Throwable rootCause = ex.getRootCause();
        if(rootCause instanceof InvalidFormatException){
            InvalidFormatException invalidFormatException = (InvalidFormatException) rootCause;
            message = "请检查参数："+ invalidFormatException.getPathReference()+",值："+invalidFormatException.getValue();
        }
        return  Response.fail(HttpStatus.BAD_REQUEST,ResultStatus.PARAM_ERROR,message);
    }

    /**
     * errorHandler 前台传参json转换错误
     * @Description:
     * @param ex
     * @return com.tydic.dev1.common.base.api.Response
     * @author
     * @date 2019/4/10 08:55
     * @throws
     */
    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity errorHandler(AuthenticationException ex) {
//        ResponseEntity.status(HttpStatus.ACCEPTED).body(ex);
//        Response<ResponseBodyError> response = ex.getResponse();
//        if(!StrUtil.isEmpty(ex.getMessage())){
//            response.getBody().setMessage(ex.getMessage());
//        }
        return Response.fail(ex.getResponse());
    }

    /**
     * busiExceptionHandler 程序主动抛出的业务异常处理
     * @Description:  
     * @param ex
     * @return com.tydic.dev1.common.base.api.Response
     * @author
     * @date 2019/4/10 08:55
     * @throws
     */
    @ResponseBody
    @ExceptionHandler(value = BusiException.class)
    public ResponseEntity busiExceptionHandler(BusiException ex) {
        return Response.fail(ex.getResponse());
    }

    /**
     * bindExceptionHandler 校验不通过异常处理
     * @Description:  
     * @param ex
     * @return com.tydic.dev1.common.base.api.Response
     * @author
     * @date 2019/4/10 08:54
     * @throws
     */
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class,BindException.class})
    public org.springframework.http.ResponseEntity bindExceptionHandler(Exception ex) {
        BindingResult bindingResult = ReflectUtil.invoke(ex,"getBindingResult");
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuffer message = new StringBuffer();
        allErrors.forEach((objectError)->{
            if(objectError instanceof FieldError){
                message.append(((FieldError) objectError).getField());
            }else{
                message.append(objectError.getObjectName());
            }
            message.append(":");
            message.append(objectError.getDefaultMessage());
            message.append(" ");
        });
        return  Response.fail(HttpStatus.BAD_REQUEST,ResultStatus.PARAM_ERROR,message.toString());
    }

}