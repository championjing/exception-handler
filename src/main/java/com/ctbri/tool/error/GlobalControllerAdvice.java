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
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    public Res errorHandler(Exception ex) {
        log.debug("e:{}" , ex);
        Res res = new Res(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage(),"系统错误，请联系管理员");
        return res;
    }
    /**
     * 参数异常捕捉处理 400
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ParamException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    public Res ResparamHandler(ParamException ex) {
        log.debug("e:{}" , ex);
        Res res = new Res( HttpStatus.BAD_REQUEST.value(), ex.getMsg(), "参数错误" );
        return res;
    }
    /**
     * 编码人员主动抛出的权限异常
     * @param ex 403
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ForbidException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseStatus(HttpStatus.OK)
    public Res paramHandler(ForbidException ex) {
        log.debug("e:{}" , ex);
        Res res = new Res( HttpStatus.FORBIDDEN.value(), ex.getMsg(), "无操作权限" );
        return res;
    }
    /**
     * springboot中参数校验，为了返回更易看懂的信息做的封装
     * @param ex 400
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    public Res argumentHandler(MethodArgumentNotValidException ex) {
        log.debug("e:{}" , ex);
        Map<String, Object> result = new HashMap<>();
        String msg = ex.getMessage();
        String[] split = msg.split("default message");
        StringBuilder sb = new StringBuilder();
        try {
            sb.append( split[ split.length-2 ] );
            sb.append( split[ split.length-1 ] );
            msg = sb.toString();
        } catch (IndexOutOfBoundsException e) {
            log.debug("检查报错情况：{}", msg);
        }
        Res res = new Res( HttpStatus.BAD_REQUEST.value(), msg, "参数错误" );
        return res;
    }

    /**
     * 当接口需要返回404时，此处的404为编码者主动抛出
     * @param e 404
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Res notFound(NotFoundException e){
        log.debug("e:{}" , e);
        Res res = new Res( HttpStatus.NOT_FOUND.value(), e.getMsg(), "资源不存在" );
        return res;
    }
}
