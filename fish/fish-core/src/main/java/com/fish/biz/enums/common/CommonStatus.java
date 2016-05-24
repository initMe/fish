package com.fish.biz.enums.common;

/**
 * @author fish
 * @date 2016/5/23
 */
public enum  CommonStatus {

    DELETED(-1, "已删除"),
    NORMAL(0, "正常，默认状态"),
    ENABLED(1, "禁用");

    private Integer code;
    private String name;

    private CommonStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
