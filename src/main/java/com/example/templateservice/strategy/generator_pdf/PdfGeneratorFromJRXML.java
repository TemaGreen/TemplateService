package com.example.templateservice.strategy.generator_pdf;

import com.example.templateservice.exceptions.GenerateDocumentException;
import com.example.templateservice.manager.FilenameManager;
import com.example.templateservice.strategy.DocumentGenerator;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;

@Slf4j
@Component("jrxml")
public class PdfGeneratorFromJRXML extends AbstractDocumentGenerator implements DocumentGenerator {

    @Autowired
    private FilenameManager filename;

    @Override
    public byte[] generate() throws GenerateDocumentException {
        Resource templateRes = resourceLoader.getResource(classPath + template.getPath());
        try (InputStream is = templateRes.getInputStream()) {
            if (!params.containsKey("classPath")) {
                params.put("classPath", resourceLoader.getResource(templateDir).getURL().toString());
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(is);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            writePdf(jasperPrint);//необязательно чисто для проверки

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            return baos.toByteArray();

        } catch (JRException | IOException   e) {
            log.error("Ошибка генерации документа. template={}", template.getPath(), e);
            throw new GenerateDocumentException("Не удалось сгенерировать документ.", e);
        } catch (InvalidPathException  e) {
            log.error("Ошибка генерации документа. Некорректное название название документа",  e);
            throw new GenerateDocumentException("Не удалось сгенерировать документ. Некорректное название название документа. " + e.getMessage(), e);
        }
    }

    private void writePdf(JasperPrint jasperPrint) throws JRException, InvalidPathException, IOException{
        Path dir = Paths.get(filePath);
        Files.createDirectories(dir);
        Path fileName = dir.resolve( filename.generate("report", ".pdf"));
        try(OutputStream os = Files.newOutputStream(fileName, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
        }
    }


}
