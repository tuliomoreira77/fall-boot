package br.com.tm.fall.boot.core;

import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.container.DependencyContainer;
import br.com.tm.fall.boot.models.ConstructorNotFoundException;
import br.com.tm.fall.boot.models.InvalidRiceBuildException;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class CoreVerifier {

    public void verifyValidConstructor(Class<?> clazz) {
        Optional<Constructor<?>> constructor = Arrays.stream(clazz.getConstructors()).filter(c -> c.getAnnotation(Injected.class) != null).findFirst();
        if (constructor.isEmpty()) {
            throw new ConstructorNotFoundException(clazz.getName());
        }
    }

    public void verifyInstancesNotNull(Set<Class<?>> rices, DependencyContainer dependencyContainer) {
        for(var rice : rices) {
            Object instance = dependencyContainer.getRice(rice);
            if(instance == null) {
                throw new InvalidRiceBuildException(rice.getName());
            }
        }
    }
}
