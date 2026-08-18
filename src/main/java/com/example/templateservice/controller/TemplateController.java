package com.example.templateservice.controller;

import com.example.templateservice.data.Template;
import com.example.templateservice.service.interfaces.TemplateService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/{id}")
    public Template getTeamplteById(@PathVariable Long id){
        return templateService.getTemplateById(id);
    }


    @GetMapping("/test")
    public void test(){
        String temaplteValue = "test1";
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Артем");
        params.put("surename", "Хамидуллин");
        List<String> temp = new ArrayList<>();
        temp.add("");
        templateService.generatePdf(temaplteValue, params, new JRBeanCollectionDataSource(temp));
        System.out.println("ok");
    }
}
