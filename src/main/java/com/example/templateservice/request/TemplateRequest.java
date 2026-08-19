package com.example.templateservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class TemplateRequest {

    @JsonProperty("templateValue")
    private String templateValue;

    @JsonProperty("params")
    private Map<String, Object> params;

    @JsonProperty("dataSource")
    private List<Object> dataSource;

    public String getTemplateValue() {
        return templateValue;
    }

    public void setTemplateValue(String templateValue) {
        this.templateValue = templateValue;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<Object> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Object> dataSource) {
        this.dataSource = dataSource;
    }

    public TemplateRequest(String templateValue, Map<String, Object> params, List<Object> dataSource) {
        this.templateValue = templateValue;
        this.params = params;
        this.dataSource = dataSource;
    }

    public TemplateRequest() {
    }
}
