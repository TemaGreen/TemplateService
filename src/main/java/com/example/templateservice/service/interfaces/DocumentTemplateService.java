package com.example.templateservice.service.interfaces;

import com.example.templateservice.data.Template;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Map;

public interface DocumentTemplateService {

    Template getTemplateById(Long id);

    byte[] generatePdf(String templateValue, Map<String, Object> params, JRBeanCollectionDataSource dataSource);
}
