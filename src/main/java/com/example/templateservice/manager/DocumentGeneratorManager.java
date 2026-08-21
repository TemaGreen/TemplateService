package com.example.templateservice.manager;

import com.example.templateservice.generators.DocumentGenerator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DocumentGeneratorManager {

    private final Map<String, DocumentGenerator> generators;

    public DocumentGeneratorManager(Map<String, DocumentGenerator> generators) {
        this.generators = generators;
    }

    public boolean contains(String key){
        return generators.containsKey(key);
    }

    public DocumentGenerator getGenerator(String key){
        return generators.get(key);
    }
}
