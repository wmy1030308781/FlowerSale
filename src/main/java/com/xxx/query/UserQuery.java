package com.xxx.query;

import com.xxx.base.BaseQuery;

public class UserQuery extends BaseQuery {
//    private Integer userid;

    private String loginname;

    private String identity;

    private String realname;

    private String address;

    private String phone;

    public UserQuery() {
    }

    public UserQuery(String loginname, String identity, String realname, String address, String phone) {
        this.loginname = loginname;
        this.identity = identity;
        this.realname = realname;
        this.address = address;
        this.phone = phone;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "loginname='" + loginname + '\'' +
                ", identity='" + identity + '\'' +
                ", realname='" + realname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
