package com.fish.biz.common;

import java.io.Serializable;

/**
 * @author fish
 * @date 2016/5/23
 */
public class BaseResult implements Serializable {


    private static final long serialVersionUID = -5065048992870359334L;

    private static final Integer SUCCESS_CODE = 0;

    public String code;

    public String message;

    public Object object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public BaseResult(){}

    public BaseResult(String message){
        this.message = message;
    }

    public BaseResult(String code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseResult(Object o){
        this.object =o;
    }

    public BaseResult(String code, String message, Object o){
        this.code = code;
        this.message = message;
        this.object =o;
    }
}
