package com.ctbri.tool.error;

/**
 * @author : championjing
 * @ClassName: ForbidException
 * @Description:
 * @Date: 2/27/2019 9:40 AM
 */
public class NotFoundException extends Exception{
    private String msg;

    public NotFoundException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
