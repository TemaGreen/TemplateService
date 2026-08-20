package com.example.templateservice.data;

import com.example.templateservice.entity.TemplateEntity;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Map;

public class TemplateDTO {

    private TemplateEntity template;

    private Map<String, Object> params;

    private JRBeanCollectionDataSource dataSource;

    public TemplateEntity getTemplate() {
        return template;
    }

    public void setTemplate(TemplateEntity template) {
        this.template = template;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public JRBeanCollectionDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(JRBeanCollectionDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public TemplateDTO(TemplateEntity template, Map<String, Object> params, JRBeanCollectionDataSource dataSource) {
        this.template = template;
        this.params = params;
        this.dataSource = dataSource;
    }

    public TemplateDTO() {
    }
}
