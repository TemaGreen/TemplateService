package com.example.templateservice.service;

import com.example.templateservice.data.Template;
import com.example.templateservice.data.TemplateDTO;
import com.example.templateservice.entity.TemplateEntity;
import com.example.templateservice.exceptions.ExtensionDocumentException;
import com.example.templateservice.exceptions.GenerateDocumentException;
import com.example.templateservice.manager.DocumentGeneratorManager;
import com.example.templateservice.mapper.TemplateMapper;
import com.example.templateservice.repository.TemplateRepository;
import com.example.templateservice.service.interfaces.TemplateService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class TemplateServiceImp implements TemplateService {

    @Autowired
    private TemplateRepository repository;

    @Autowired
    private TemplateMapper mapper;

    @Autowired
    private DocumentGeneratorManager manager;

    @Override
    public Template getTemplateById(Long id) {
        return mapper.entityToModel(repository.getTemplateEntitiesById(id));
    }

    public byte[] generatePdf(String templateValue, Map<String, Object> params, JRBeanCollectionDataSource dataSource) {
        TemplateEntity template = repository.getTemplateEntitiesByValue(templateValue);
        try {
            if (template == null || template.getPath() == null || template.getPath().isEmpty())
                throw new FileNotFoundException("Файл не найден. templateValue = " + templateValue);
            int lastDot = template.getPath().lastIndexOf('.');
            String extensions = template.getPath().substring(lastDot + 1);
            if (!manager.contains(extensions))
                throw new ExtensionDocumentException("Неизвестное расширение. Не найден способ генерации документа.");
            return manager.getGenerator(extensions).templateDTO(new TemplateDTO(template, params, dataSource)).generate();
        } catch (FileNotFoundException | ExtensionDocumentException e){
            log.error("Ошибка генерации документа. template={}", templateValue, e);
            throw new GenerateDocumentException("Не удалось сгенерировать документ.", e);
        }
    }
}