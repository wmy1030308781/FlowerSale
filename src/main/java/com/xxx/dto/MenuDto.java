package com.xxx.dto;

public class MenuDto {
    private Integer id;

    private Integer pId;

    private String name;

    private Boolean checked=false;

    public MenuDto() {
    }

    public MenuDto(Integer id, Integer pId, String name, Boolean checked) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", checked=" + checked +
                '}';
    }
}
