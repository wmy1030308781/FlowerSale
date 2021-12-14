package com.xxx.bean;

import java.util.Date;

public class BusRent {
    private String saleid;

    private Double price;

    private Date begindate;

    private Date saledate;

    private Integer saleflag;

    private String identity;

    private String flowernumber;

    private String opername;

    private Date createtime;

    public String getSaleid() {
        return saleid;
    }

    public void setSaleid(String saleid) {
        this.saleid = saleid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getSaledate() {
        return saledate;
    }

    public void setSaledate(Date saledate) {
        this.saledate = saledate;
    }

    public Integer getSaleflag() {
        return saleflag;
    }

    public void setSaleflag(Integer saleflag) {
        this.saleflag = saleflag;
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

    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}