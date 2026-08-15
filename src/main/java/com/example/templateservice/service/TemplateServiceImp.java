package com.example.templateservice.service;

import com.example.templateservice.data.Template;
import com.example.templateservice.entity.TemplateEntity;
import com.example.templateservice.mapper.TemplateMapper;
import com.example.templateservice.repository.TemplateRepository;
import com.example.templateservice.service.interfaces.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImp implements TemplateService {

    @Autowired
    private TemplateRepository repository;

    @Autowired
    private TemplateMapper mapper;

    @Override
    public Template getTemplateById(Long id) {
        return mapper.entityToModel(repository.getTemplateEntitiesById(id));
    }
}
