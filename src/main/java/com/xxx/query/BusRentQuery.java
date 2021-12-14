package com.xxx.query;


import com.xxx.base.BaseQuery;

public class BusRentQuery extends BaseQuery {
    private String saleid;
    private String identity;
    private String flowernumber;
    private String begindate;
    private String saledate;

    public String getSaleid() {
        return saleid;
    }

    public void setSaleid(String saleid) {
        this.saleid = saleid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFlowernumber() {
        return flowernumber;
    }

    public void setFlowernumber(String flowernumber) {
        this.flowernumber = flowernumber;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }
}
