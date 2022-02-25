package br.com.tm.fall.boot.models;

public class InvalidRiceBuildException extends RuntimeException {

    public InvalidRiceBuildException(String className) {
        super("Error building Rice instace - className: " + className);
    }
}
