package com.example.templateservice.controller;

import com.example.templateservice.data.Template;
import com.example.templateservice.service.interfaces.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/{id}")
    public Template getTeamplteById(@PathVariable Long id){
        return templateService.getTemplateById(id);
    }
}
