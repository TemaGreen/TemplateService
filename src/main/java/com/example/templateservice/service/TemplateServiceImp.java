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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.UUID;

@Service
public class TemplateServiceImp implements TemplateService {

    @Autowired
    private TemplateRepository repository;

    @Autowired
    private TemplateMapper mapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${template.classpath}")
    private String classPath;

    @Value("${template.dir}")
    private String templateDir;

    @Value("${data.output.pdf}")
    private String filePath;

    @Override
    public Template getTemplateById(Long id) {
        return mapper.entityToModel(repository.getTemplateEntitiesById(id));
    }

    public void generatePdf(String templateValue, Map<String, Object> params, JRBeanCollectionDataSource dataSource) {
        TemplateEntity template = repository.getTemplateEntitiesByValue(templateValue);
        Resource templateRes = resourceLoader.getResource(classPath + template.getPath());

        dataSource.getRecordCount();

        try(InputStream is = templateRes.getInputStream()) {
            if (!params.containsKey("classPath")) {
                params.put("classPath", resourceLoader.getResource(templateDir).getURL().toString());
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(is);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            Path dir = Paths.get(filePath);
            Files.createDirectories(dir);
            Path fileName = dir.resolve("report_" + UUID.randomUUID() + ".pdf");
            try(OutputStream os = Files.newOutputStream(fileName, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
                JasperExportManager.exportReportToPdfStream(jasperPrint, os);
            }
        } catch (JRException e) {
            System.err.println("getJasperPrint {}" + e.getMessage());
        } catch (IOException e){
            System.err.println("create directory failed");
        }

    }

    public String getDictionaryByTemplateValue(String templateValue){
        return "";
    }
}
