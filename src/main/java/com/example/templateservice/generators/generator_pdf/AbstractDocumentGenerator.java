package com.example.templateservice.generators.generator_pdf;

import com.example.templateservice.data.TemplateDTO;
import com.example.templateservice.entity.TemplateEntity;
import com.example.templateservice.generators.DocumentGenerator;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;

import java.util.Map;

public abstract class AbstractDocumentGenerator implements DocumentGenerator {

    @Autowired
    protected ResourceLoader resourceLoader;

    @Value("${template.classpath}")
    protected String classPath;

    @Value("${template.dir}")
    protected String templateDir;

    @Value("${data.output.pdf}")
    protected String filePath;

    protected TemplateEntity template;

    protected Map<String, Object> params;

    protected JRBeanCollectionDataSource dataSource;

    @Override
    public void setTemplateDTO(TemplateDTO template) {
        this.template = template.getTemplate();
        this.params = template.getParams();
        this.dataSource = template.getDataSource();
    }

    @Override
    public DocumentGenerator templateDTO(TemplateDTO template) {
        this.template = template.getTemplate();
        this.params = template.getParams();
        this.dataSource = template.getDataSource();
        return this;
    }
}
