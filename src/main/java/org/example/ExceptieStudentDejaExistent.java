package org.example;

public class ExceptieStudentDejaExistent extends RuntimeException {
    public ExceptieStudentDejaExistent(String message) {
        super(message);
    }
}