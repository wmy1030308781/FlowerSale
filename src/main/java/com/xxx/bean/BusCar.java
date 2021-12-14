package com.xxx.bean;


import java.util.Date;

public class BusCar {
    private String flowernumber;

    private String flowertype;

    private String color;

    private Double Purchaseprice;

    private Double price;

    private Double deposit;

    private Integer issaleing;

    private String description;

    private String flowerimg;

    private Date createtime;

    public String getFlowernumber() {
        return flowernumber;
    }

    public void setFlowernumber(String flowernumber) {
        this.flowernumber = flowernumber;
    }

    public String getFlowertype() {
        return flowertype;
    }

    public void setFlowertype(String flowertype) {
        this.flowertype = flowertype;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPurchaseprice() {
        return Purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        Purchaseprice = purchaseprice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Integer getIssaleing() {
        return issaleing;
    }

    public void setIssaleing(Integer issaleing) {
        this.issaleing = issaleing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlowerimg() {
        return flowerimg;
    }

    public void setFlowerimg(String flowerimg) {
        this.flowerimg = flowerimg;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "BusCar{" +
                "flowernumber='" + flowernumber + '\'' +
                ", flowertype='" + flowertype + '\'' +
                ", color='" + color + '\'' +
                ", Purchaseprice=" + Purchaseprice +
                ", price=" + price +
                ", deposit=" + deposit +
                ", issaleing=" + issaleing +
                ", description='" + description + '\'' +
                ", flowerimg='" + flowerimg + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}