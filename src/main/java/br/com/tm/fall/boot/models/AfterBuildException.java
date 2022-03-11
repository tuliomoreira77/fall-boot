package br.com.tm.fall.boot.models;

public class AfterBuildException extends RuntimeException {

    public AfterBuildException(String className, String methodName) {
        super("Error executing after build method: " + className + "." + methodName);
    }
}
