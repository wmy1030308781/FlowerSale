package com.xxx.query;

import com.xxx.base.BaseQuery;

public class RoleQuery extends BaseQuery {
    private String rolename;
    private String roledesc;

    public RoleQuery() {
    }

    public RoleQuery(String rolename, String roledesc) {
        this.rolename = rolename;
        this.roledesc = roledesc;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Override
    public String toString() {
        return "RoleQuery{" +
                "rolename='" + rolename + '\'' +
                ", roledesc='" + roledesc + '\'' +
                '}';
    }
}
