package com.ctbri.tool.error;

import org.springframework.http.HttpStatus;

/**
 * @author : championjing
 * @ClassName: Result
 * @Description:
 * @Date: 2019/12/25 0025 12:49
 */
public class Res {

    public final static Res OK = new Res( HttpStatus.OK.value(),"成功","");

    private Integer code;
    private String msg;
    private String message;

    public Res(Integer code,String msg, String message){
        this.msg = msg;
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMessage() {
        return message;
    }
}
