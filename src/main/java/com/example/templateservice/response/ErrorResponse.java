package com.example.templateservice.response;

public class ErrorResponse {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse() {
    }
}
