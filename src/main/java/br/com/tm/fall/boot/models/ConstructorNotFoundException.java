package br.com.tm.fall.boot.models;

public class ConstructorNotFoundException extends RuntimeException {

    public ConstructorNotFoundException(String className) {
        super("Valid constructor not found in class: " + className);
    }
}
