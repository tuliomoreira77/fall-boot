package br.com.tm.fall.boot.core;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassScanner {

    public static <T extends Annotation> Set<Class<?>> scanPackages(Class<T> clazz, String... packages) {
        Reflections reflections = new Reflections();
        for(String packageToScan : packages) {
            reflections.merge(new Reflections((packageToScan)));
        }
        return reflections.getTypesAnnotatedWith(clazz);
    }

}
