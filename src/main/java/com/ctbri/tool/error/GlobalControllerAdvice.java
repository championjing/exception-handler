package com.ctbri.tool.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : championjing
 * @ClassName: ParamException
 * @Description: msg 为详细描述信息，前端可显示该内容；
 *                message为概括信息；
 *
 * @Date: 2019/12/24 0024 11:58
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    private final static Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    public GlobalControllerAdvice(){
        log.info("实例化全局异常捕获-");
    }
    /**
     * 全局异常捕捉处理 500
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> errorHandler(Exception ex) {
        log.warn("e:{}" , ex);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", ex.getMessage() );
        result.put("message", "系统错误，请联系管理员");
        return result;
    }
    /**
     * 参数异常捕捉处理 400
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ParamException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> paramHandler(ParamException ex) {
        log.warn("e:{}" , ex);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", ex.getMsg() );
        result.put("message", "param error" );
        return result;
    }
    /**
     * 权限异常捕捉处理
     * @param ex 403
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ForbidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> paramHandler(ForbidException ex) {
        log.warn("e:{}" , ex);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", ex.getMsg() );
        result.put("message", "无操作权限" );
        return result;
    }
    /**
     * springboot中参数校验，为了返回更易看懂的信息做的封装
     * @param ex 400
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> argumentHandler(MethodArgumentNotValidException ex) {
        log.warn("e:{}" , ex);
        Map<String, Object> result = new HashMap<>();
        String msg = ex.getMessage();
        String[] split = msg.split("default message");
        StringBuilder sb = new StringBuilder();
        try {
            sb.append( split[ split.length-2 ] );
            sb.append( split[ split.length-1 ] );
            msg = sb.toString();
        } catch (IndexOutOfBoundsException e) {
            log.warn("检查报错情况：{}", msg);
        }
        log.info("错误信息:{}",ex.getMessage());
        result.put("msg", msg);
        result.put("message", "参数错误");
        return result;
    }

    /**
     * 当接口需要返回404时
     * @param e 404
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFound(NotFoundException e){
        log.warn("e:{}" , e);
        Map<String, Object> result = new HashMap<>();
        log.info("错误信息:{}",e.getMessage());
        result.put("msg", e.getMessage() );
        result.put("message", "参数错误");
        return result;
    }
}
