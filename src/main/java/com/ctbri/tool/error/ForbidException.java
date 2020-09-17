package com.ctbri.tool.error;

/**
 * @author : championjing
 * @ClassName: ForbidException
 * @Description:
 * @Date: 2/27/2019 9:40 AM
 */
public class ForbidException extends Exception{

    private String msg;

    public ForbidException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
