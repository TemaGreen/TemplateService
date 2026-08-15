package com.example.templateservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="template")
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "template_sequence")
    @SequenceGenerator(name = "template_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "path")
    private String path;

    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
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

    public TemplateEntity(Long id, String name, String value, Date createDate, String path, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createDate = createDate;
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TemplateEntity(String name, String value, Date createDate, String path, Date startDate, Date endDate) {
        this.name = name;
        this.value = value;
        this.createDate = createDate;
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TemplateEntity() {
    }
}
