package com.example.templateservice.data;

import java.util.Date;

public class Template {

    private Long id;

    private String name;

    private String value;

    private Date createDate;

    private String path;

    private Date startDate;

    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Template(Long id, String name, String value, Date createDate, String path, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createDate = createDate;
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Template(String name, String value, Date createDate, String path, Date startDate, Date endDate) {
        this.name = name;
        this.value = value;
        this.createDate = createDate;
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Template() {
    }
}
