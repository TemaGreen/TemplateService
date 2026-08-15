package com.example.templateservice.repository;

import com.example.templateservice.entity.TemplateEntity;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<TemplateEntity, Integer>{

    TemplateEntity getTemplateEntitiesById(Long id);
}
