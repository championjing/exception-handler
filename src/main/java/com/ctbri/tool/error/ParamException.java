package com.ctbri.tool.error;

/**
 * @author : championjing
 * @ClassName: ParamException
 * @Description:
 * @Date: 2019/2/24 0024 11:58
 */
public class ParamException extends Exception{
    private String msg;

    public ParamException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
