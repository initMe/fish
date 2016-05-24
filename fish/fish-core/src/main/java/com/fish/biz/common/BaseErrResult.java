package com.fish.biz.common;

import org.apache.commons.lang.StringUtils;

/**
 * @author fish
 * @date 2016/5/23
 */
public class BaseErrResult extends BaseResult {

    public BaseErrResult(){
        super();
    }

    public BaseErrResult(String message){
        super(message);
    }

    public BaseErrResult(String code, String message){
        super(code,message);
    }

    public BaseErrResult(Object o){
        super(o);
    }

    public BaseErrResult(String code, String message, Object o){
        super(code,message,o);
    }

    public Boolean isError(){

        if (StringUtils.isNotBlank(code) && !StringUtils.equals(code,"0")){
            return true;
        }

        if (StringUtils.isNotBlank(message)){
            return true;
        }

        return false;
    }

}
