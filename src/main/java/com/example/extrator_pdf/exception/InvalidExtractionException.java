package com.example.extrator_pdf.exception;

import java.io.Serial;

public class InvalidExtractionException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorMessage;

    public InvalidExtractionException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
