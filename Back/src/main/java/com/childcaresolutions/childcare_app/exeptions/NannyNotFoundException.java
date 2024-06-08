package com.childcaresolutions.childcare_app.exeptions;

public class NannyNotFoundException extends RuntimeException {
    public NannyNotFoundException(Long id) {
        super("Nanny with ID " + id + " not found.");
    }

    public NannyNotFoundException(String message) {
        super(message);
    }

    public NannyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}