package com.example.templateservice.generators.generator_pdf;

import com.example.templateservice.exceptions.GenerateDocumentException;
import com.example.templateservice.generators.DocumentGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component("pdf")
public class PdfGenerator extends AbstractDocumentGenerator implements DocumentGenerator {

    @Override
    public byte[] generate() throws GenerateDocumentException {
        Resource templateRes = resourceLoader.getResource(classPath + template.getPath());
        try {
            if (!templateRes.exists()) {
                throw new FileNotFoundException("Файл не найден. Path=" + template.getPath());
            }
            try (InputStream inputStream = templateRes.getInputStream()) {
                return FileCopyUtils.copyToByteArray(inputStream);
            }
        } catch (IOException e){
            log.error("Ошибка генерации документа. template={}", template.getPath(), e);
            throw new GenerateDocumentException("Не удалось сгенерировать документ", e);
        }
    }
}
