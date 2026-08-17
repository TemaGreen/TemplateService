package com.example.templateservice.service;

import com.example.templateservice.data.Template;
import com.example.templateservice.entity.TemplateEntity;
import com.example.templateservice.mapper.TemplateMapper;
import com.example.templateservice.repository.TemplateRepository;
import com.example.templateservice.service.interfaces.TemplateService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class TemplateServiceImp implements TemplateService {

    @Autowired
    private TemplateRepository repository;

    @Autowired
    private TemplateMapper mapper;

    @Value("template.path")
    private String root;

    @Override
    public Template getTemplateById(Long id) {
        return mapper.entityToModel(repository.getTemplateEntitiesById(id));
    }

    public void generatePdf(String templateValue, Map<String, Object> params, JRBeanCollectionDataSource dataSource) {
        JasperPrint jasperPrint = null;
        try {
            TemplateEntity template = repository.getTemplateEntitiesByValue(templateValue);
            if (!params.containsKey("classPath")) {
                params.put("classPath", root);
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(root + template.getPath());
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        } catch (JRException e) {
            System.err.println("getJasperPrint {}" + e.getMessage());
        }

    }

    public String getDictionaryByTemplateValue(String templateValue){
        return "";
    }
}
