package com.example.templateservice.exceptions;

public class GenerateDocumentException extends RuntimeException {

    public GenerateDocumentException(String message) {
        super(message);
    }

    public GenerateDocumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
