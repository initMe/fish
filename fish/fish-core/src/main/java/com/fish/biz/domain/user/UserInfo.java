package com.fish.biz.domain.user;

import java.util.Date;

public class UserInfo {
    /** 主键 */
    private Long id;

    /** 用户id */
    private Long userId;

    /** 性别 */
    private Integer sex;

    /** 生日 */
    private Date birthday;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 区/县 */
    private String distirct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistirct() {
        return distirct;
    }

    public void setDistirct(String distirct) {
        this.distirct = distirct == null ? null : distirct.trim();
    }
}