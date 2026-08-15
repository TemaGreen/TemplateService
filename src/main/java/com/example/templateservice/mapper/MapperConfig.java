package com.example.templateservice.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public TemplateMapper templateMapper(){
        return Mappers.getMapper(TemplateMapper.class);
    }
}
