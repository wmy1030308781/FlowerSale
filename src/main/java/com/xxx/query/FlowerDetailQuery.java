package com.xxx.query;

import com.xxx.base.BaseQuery;

public class FlowerDetailQuery extends BaseQuery {
    private String flowernumber;
    private String flowertype;
    private String color;
    private String description;
    private Integer issaleing;

    public FlowerDetailQuery() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIssaleing() {
        return issaleing;
    }

    public void setIssaleing(Integer issaleing) {
        this.issaleing = issaleing;
    }

    @Override
    public String toString() {
        return "BusCarQuery{" +
                "flowernumber='" + flowernumber + '\'' +
                ", flowertype='" + flowertype + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", issaleing=" + issaleing +
                '}';
    }
}
