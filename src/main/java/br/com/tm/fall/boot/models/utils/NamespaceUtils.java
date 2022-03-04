package br.com.tm.fall.boot.models.utils;

public class NamespaceUtils {

    private NamespaceUtils() {}

    public static String generateRiceName(Class<?> clazz) {
        return clazz.getSimpleName();
    }
}
