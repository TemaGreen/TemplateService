package com.example.templateservice.mapper;

import com.example.templateservice.data.Template;
import com.example.templateservice.entity.TemplateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface TemplateMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "createDate", target = "createDate"),
            @Mapping(source = "path", target = "path"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate")
    })
    TemplateEntity modelToEntity(Template model);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "createDate", target = "createDate"),
            @Mapping(source = "path", target = "path"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate")
    })
    Template entityToModel(TemplateEntity entity);
}
