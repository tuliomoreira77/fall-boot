package br.com.tm.fall.boot.core;


import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.container.DependencyContainer;
import br.com.tm.fall.boot.models.ConstructorNotFoundException;
import br.com.tm.fall.boot.models.InvalidRiceBuildException;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InstanceBuilder {

    private final DependencyContainer dependencyContainer;

    public InstanceBuilder(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    public void verifyValidConstructor(Class<?> clazz) {
        Optional<Constructor<?>> constructor = Arrays.stream(clazz.getConstructors()).filter(c -> c.getAnnotation(Injected.class) != null).findFirst();
        if (constructor.isEmpty()) {
            throw new ConstructorNotFoundException(clazz.getName());
        }
    }

    public Object buildInstance(Class<?> clazz) {
        try {
            Optional<Constructor<?>> constructor = Arrays.stream(clazz.getConstructors()).filter(c -> c.getAnnotation(Injected.class) != null).findFirst();
            var parametersInstances = getParametersInstances(constructor.get().getParameterTypes());
            if(parametersInstances == null) return null;

            return constructor.get().newInstance(parametersInstances);
        } catch (Exception e) {
            throw new InvalidRiceBuildException(clazz.getName());
        }
    }

    private Object[] getParametersInstances(Class<?>[] parameters) {
        List<Object> instances = new ArrayList<>();
        for(var parameter : parameters) {
            Object instance = dependencyContainer.getRice(parameter);
            if(instance == null) return null;

            instances.add(dependencyContainer.getRice(parameter));
        }
        return instances.toArray();
    }

}
