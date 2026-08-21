package com.example.templateservice.controller;

import com.example.templateservice.data.Template;
import com.example.templateservice.exceptions.GenerateDocumentException;
import com.example.templateservice.request.TemplateRequest;
import com.example.templateservice.response.ErrorResponse;
import com.example.templateservice.service.interfaces.DocumentTemplateService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@Slf4j
@RestController
@RequestMapping("/template")
public class DocumentTemplateController {

    @Autowired
    private DocumentTemplateService templateService;

    @GetMapping("/{id}")
    public Template getTeamplteById(@PathVariable Long id){
        return templateService.getTemplateById(id);
    }


    @GetMapping("/test")
    public String test(){
        return "";
    }

    @PostMapping("/generate")
    public ResponseEntity<Resource> generateTemplate(@RequestBody TemplateRequest templateRequest){
            byte[] pdfFile = templateService.generatePdf(templateRequest.getTemplateValue(), templateRequest.getParams(), new JRBeanCollectionDataSource(templateRequest.getDataSource()));
            Resource resource = new ByteArrayResource(pdfFile);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.pdf\"");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfFile.length)
                    .body(resource);
    }

    @ExceptionHandler({GenerateDocumentException.class, FileNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlerGenerateDocument(RuntimeException ex){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handlerException(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }
}
