package com.example.templateservice.generators;

import com.example.templateservice.data.TemplateDTO;
import com.example.templateservice.exceptions.GenerateDocumentException;

public interface DocumentGenerator {

    void setTemplateDTO(TemplateDTO templateDTO);

    DocumentGenerator templateDTO(TemplateDTO templateDTO);

    byte[] generate() throws GenerateDocumentException;
}
