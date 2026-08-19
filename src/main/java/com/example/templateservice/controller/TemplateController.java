package com.example.templateservice.controller;

import com.example.templateservice.data.Template;
import com.example.templateservice.request.TemplateRequest;
import com.example.templateservice.service.interfaces.TemplateService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public TemplateRequest test(){
        String temaplteValue = "test1";
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Артем");
        params.put("surename", "Хамидуллин");
        List<Object> temp = new ArrayList<>();
        temp.add("");
        //templateService.generatePdf(temaplteValue, params, new JRBeanCollectionDataSource(temp));
        System.out.println("ok");
        return new TemplateRequest(temaplteValue, params, temp);
    }

    @PostMapping("/generate")
    public ResponseEntity generateTemplate(@RequestBody TemplateRequest templateRequest){
        templateService.generatePdf(templateRequest.getTemplateValue(), templateRequest.getParams(), new JRBeanCollectionDataSource(templateRequest.getDataSource()));
        return ResponseEntity.ok("OK");
    }
}
